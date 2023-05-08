package com.spotlight.platform.userprofile.api.core.profile;
import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidCommandException;
import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidIncrementValueException;
import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidNonIntPropertyIncrementException;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.ICommandProcessFactory;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.ICommandProcessor;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

public class UserProfileService {
    private final UserProfileDao userProfileDao;
    //Simple logging, in other case we might need more proper logging mechanism and framework
    private ILogger logger;
    private ICommandProcessFactory commandProcessforFactory;

    @Inject
    public UserProfileService(UserProfileDao userProfileDao,ILogger logger,ICommandProcessFactory commandProcessforFactory) {
        this.logger = logger;
        this.commandProcessforFactory = commandProcessforFactory;


        logger.info("start initializing user profile service");
        this.userProfileDao = userProfileDao;
        this.logger = logger;
        logger.info("Initializing user profile service completed");
    }

    public Optional<UserProfile> get(UserId userId) {
        logger.info("start user profile by id service");
        return userProfileDao.get(userId);
    }

    public  void applyCommand(UserId userId,String command,
    Map<UserProfilePropertyName, UserProfilePropertyValue> properties) 
    throws InvalidCommandException, InvalidIncrementValueException,InvalidNonIntPropertyIncrementException
    {
        logger.info("start user profile command service");
        logger.info(String.format("Command %s",command));

        var profile = userProfileDao.get(userId).orElse(null);

        Map<UserProfilePropertyName,UserProfilePropertyValue> updatedProperties = 
        new HashMap<>();        
        ICommandProcessor commandProcessor = commandProcessforFactory.getCommandProcessor(command);
        updatedProperties = commandProcessor.process(profile,properties);            
        addUpdateProfile(userId, updatedProperties);
    }  

    

    private void addUpdateProfile(UserId userId,
    Map<UserProfilePropertyName, UserProfilePropertyValue> updatedProperties) {
        var profile = userProfileDao.get(userId).orElse(null);

        if(profile == null)
        {
            logger.info(String.format("user not found, create new user with id %s", userId));
            profile = new UserProfile(userId,Instant.now(),updatedProperties);
        }
        else
        {
            logger.info("update user properties");        
            for (var entry : updatedProperties.entrySet())
            {
                profile.userProfileProperties().put(entry.getKey(),entry.getValue());
            }
        }
        
        userProfileDao.addUpdate(profile);
        logger.info(String.format("Profile added successfully"));
    }

    
}
