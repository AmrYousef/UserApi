package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidIncrementValueException;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public class IncrementCommandProcessor extends BaseCommandProcessor
{
    @Inject
    public IncrementCommandProcessor(ILogger logger) {        
        super(logger);
    }

    @Override
    public Map<UserProfilePropertyName, UserProfilePropertyValue> applyCommand(UserProfile profile,
            Map<UserProfilePropertyName, UserProfilePropertyValue> incrementProperties) {

        Map<UserProfilePropertyName,UserProfilePropertyValue> updatedProperties = 
        new HashMap<>();

        if(profile == null)
        {
            updatedProperties = incrementProperties;
        }
        else{
            for (var entry : incrementProperties.entrySet())
            {
                var increment = entry.getValue();
                var oldValue = profile.userProfileProperties().containsKey(entry.getKey()) ?
                                                                profile.userProfileProperties().get(entry.getKey()):
                                                                UserProfilePropertyValue.valueOf(0);
                
                updatedProperties.put(entry.getKey(), UserProfilePropertyValue.Increment(oldValue, increment));
            }
        }
        return updatedProperties;        
        
    }

    @Override
    public void validate(UserProfile profile,
    Map<UserProfilePropertyName,UserProfilePropertyValue> properties) {
        
        if(!properties.entrySet().stream().allMatch(e -> e.getValue().IsInt()))
        {
            logger.error("Invalid increment property value");
            throw new InvalidIncrementValueException();
        }
    }
}