package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import java.util.Map;

import com.google.inject.Inject;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public class ReplaceCommandProcessor extends BaseCommandProcessor
{
    @Inject
    public ReplaceCommandProcessor(ILogger logger) {        
        super(logger);
    }
    
    @Override
    public Map<UserProfilePropertyName, UserProfilePropertyValue> applyCommand(UserProfile profile,
            Map<UserProfilePropertyName, UserProfilePropertyValue> replaceProperties) {
        return replaceProperties;
    }

    @Override
    public void validate(UserProfile profile, Map<UserProfilePropertyName, UserProfilePropertyValue> properties) {
    }

}