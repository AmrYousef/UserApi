package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public class CollectCommandProcessor extends BaseCommandProcessor
{
    @Inject
    public CollectCommandProcessor(ILogger logger) {        
        super(logger);
    }

    @Override
    public Map<UserProfilePropertyName, UserProfilePropertyValue> applyCommand(UserProfile profile,
            Map<UserProfilePropertyName, UserProfilePropertyValue> collectedItems) {

        Map<UserProfilePropertyName,UserProfilePropertyValue> updatedProperties = 
        new HashMap<>();

        if(profile == null)
        {
            updatedProperties = collectedItems;
        }
        else{

            for (var entry : collectedItems.entrySet())
            {
                UserProfilePropertyValue updatedProfile = null;
                
                if(!profile.userProfileProperties().containsKey(entry.getKey()))
                    updatedProfile = entry.getValue();
                else{
                    
                    logger.info("Apply command - here");
                    updatedProfile = UserProfilePropertyValue.MergeLists(profile.userProfileProperties().get(entry.getKey()),
                    entry.getValue());   
                }
                updatedProperties.put(entry.getKey(), updatedProfile);
            }
        }
        return updatedProperties;
    }

    @Override
    public void validate(UserProfile profile, Map<UserProfilePropertyName, UserProfilePropertyValue> properties) {
        
    }
    

}