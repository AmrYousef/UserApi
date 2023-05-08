package com.spotlight.platform.userprofile.api.model.profile.primitives;

import com.spotlight.platform.helpers.FixtureHelpers;
import com.spotlight.platform.userprofile.api.model.common.Command;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;

import java.time.Instant;
import java.util.Map;

public class UserProfileFixtures {
        public static final String USER_ID_VALUE = "existing-user-id";
    public static final UserId USER_ID = UserId.valueOf(USER_ID_VALUE);
    public static final UserId NON_EXISTING_USER_ID = UserId.valueOf("non-existing-user-id");
    public static final UserId INVALID_USER_ID = UserId.valueOf("invalid-user-id-%");

    public static final String REPLACE_COMMAND_TYPE = "replace";
    public static final String INCREMENT_COMMAND_TYPE = "increment";
    public static final String COLLECT_COMMAND_TYPE = "collect";
    public static final String WRONG_COMMAND_TYPE = "wrong";

    public static final Instant LAST_UPDATE_TIMESTAMP = Instant.parse("2021-06-01T09:16:36.123Z");

    public static final UserProfile USER_PROFILE = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("property1"), UserProfilePropertyValue.valueOf("property1Value")));

    public static final UserProfile USER_PROFILE_INCREMENT = new UserProfile(USER_ID, LAST_UPDATE_TIMESTAMP,
            Map.of(UserProfilePropertyName.valueOf("property1"), UserProfilePropertyValue.valueOf("property1Value"),
            UserProfilePropertyName.valueOf("battleFought"), UserProfilePropertyValue.valueOf(100)));
    public static final String SERIALIZED_USER_PROFILE = FixtureHelpers.fixture("/fixtures/model/profile/userProfile.json");

    
    public static final Map<UserProfilePropertyName,UserProfilePropertyValue> COLLECTION_COMMAND_PROPERTIES = 
            Map.of(UserProfilePropertyName.valueOf("inventory"), UserProfilePropertyValue.valueOf("sword20"));
            
    public static final Map<UserProfilePropertyName,UserProfilePropertyValue> COLLECTION_MERGE_PROFILE = 
    Map.of(UserProfilePropertyName.valueOf("inventory"), UserProfilePropertyValue.valueOf("sword20"));


    public static final Map<UserProfilePropertyName,UserProfilePropertyValue> INCREMENT_COMMAND_PROPERTIES = 
            Map.of(UserProfilePropertyName.valueOf("battleFought"), UserProfilePropertyValue.valueOf(10));

    public static final Map<UserProfilePropertyName,UserProfilePropertyValue> INCREMENT_MERGE_NULL_PROFILE = 
        Map.of(UserProfilePropertyName.valueOf("battleFought"), UserProfilePropertyValue.valueOf(10));

        public static final Map<UserProfilePropertyName,UserProfilePropertyValue> INCREMENT_MERGE_PROFILE = 
        Map.of(UserProfilePropertyName.valueOf("battleFought"), UserProfilePropertyValue.valueOf(110));


    public static final Command TEST_COMMAND = new Command(USER_ID_VALUE,INCREMENT_COMMAND_TYPE,INCREMENT_COMMAND_PROPERTIES);
    public static final Command WRONG_TEST_COMMAND = new Command(USER_ID_VALUE,WRONG_COMMAND_TYPE,INCREMENT_COMMAND_PROPERTIES);
}
