package com.spotlight.platform.userprofile.api.model.common;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyName;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfilePropertyValue;

public record Command(
    @NotNull(message = "Command userId cannot be null") 
    @Size(min = 1, message = "Command userId must not be empty or more than 10 characters")
    @JsonProperty String userId,
    @NotNull(message = "Command type cannot be null") 
    @Size(min = 1,max = 10, message = "Command type must not be empty or more than 10 characters")
    @JsonProperty String type,
    @Size(min = 1, message = "Properties must not be empty")
    @JsonProperty Map<UserProfilePropertyName, UserProfilePropertyValue> properties) {
}