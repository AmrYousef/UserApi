package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import java.util.Map;

import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public abstract class BaseCommandProcessor implements ICommandProcessor{
    protected ILogger logger;

    public BaseCommandProcessor(ILogger logger) {
        this.logger= logger;
    }



    public abstract Map<UserProfilePropertyName,UserProfilePropertyValue> applyCommand(UserProfile profile,
    Map<UserProfilePropertyName,UserProfilePropertyValue> properties);

    public abstract void validate(UserProfile profile,
    Map<UserProfilePropertyName,UserProfilePropertyValue> properties);

    public Map<UserProfilePropertyName,UserProfilePropertyValue> process(UserProfile profile,
    Map<UserProfilePropertyName,UserProfilePropertyValue> properties)
    {

        logger.info("Starting %s".formatted(this.getClass().getName()));

        logger.info("Started validating parameters");        
        validate(profile, properties);
        logger.info("Parameters validated succesfully");
        
        
        logger.info("Start processed command");
        var updatedProperties = applyCommand(profile, properties);        
        logger.info("Command processed successfully");
        return updatedProperties;
    }
}
