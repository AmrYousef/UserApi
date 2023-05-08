package com.spotlight.platform.userprofile.api.core.profile.persistence;

import com.spotlight.platform.userprofile.api.core.exceptions.persistance.InvalidUserProfileException;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class UserProfileDaoInMemory implements UserProfileDao {
    private final Map<UserId, UserProfile> storage = new ConcurrentHashMap<>();
    //Simple logging, in other case we might need more proper logging mechanism and framework
    private static final Logger logger = Logger.getLogger(UserProfileDaoInMemory.class.getName());

    @Override
    public Optional<UserProfile> get(UserId userId) {
        return Optional.ofNullable(storage.get(userId));
    }

    @Override
    public void addUpdate(UserProfile userProfile) throws InvalidUserProfileException{
        if(userProfile == null)
            throw new InvalidUserProfileException();
        else
        {
            if(!storage.containsKey(userProfile.userId()))
                logger.info(String.format("user not found, adding user to storage"));
            else
                logger.info(String.format("updating user properties in storage"));

            storage.put(userProfile.userId(), userProfile);
        }
    }
}
