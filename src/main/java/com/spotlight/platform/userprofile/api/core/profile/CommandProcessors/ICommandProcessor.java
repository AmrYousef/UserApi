package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import java.util.Map;

import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public interface ICommandProcessor
{
    Map<UserProfilePropertyName,UserProfilePropertyValue> process(UserProfile profile,
    Map<UserProfilePropertyName,UserProfilePropertyValue> collectedItems);
}