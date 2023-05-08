package com.spotlight.platform.userprofile.api.core.profile;

import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.BaseCommandProcessor;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.CommandProcessFactory;
import com.spotlight.platform.userprofile.api.core.profile.CommandProcessors.ReplaceCommandProcessor;
import com.spotlight.platform.userprofile.api.core.profile.persistence.UserProfileDao;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import com.spotlight.platform.userprofile.api.core.logging.PhoenixLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserProfileServiceTest {
    private final UserProfileDao userProfileDaoMock = mock(UserProfileDao.class);
    private final PhoenixLogger loggerMock = mock(PhoenixLogger.class);
    private final CommandProcessFactory commandProcessFactoryMock = mock(CommandProcessFactory.class);

    private final BaseCommandProcessor commandProcessorMock = mock(ReplaceCommandProcessor.class);


    private final UserProfileService userProfileService = new UserProfileService(userProfileDaoMock,loggerMock,commandProcessFactoryMock);

    @Nested
    @DisplayName("get")
    class Get {
        @Test
        void getForExistingUser_returnsUser() {
            when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));

            assertThat(userProfileService.get(UserProfileFixtures.USER_ID)).usingRecursiveComparison()
                    .isEqualTo(Optional.of(UserProfileFixtures.USER_PROFILE));
        }

        @Test
        void getForNonExistingUser_throwsException() {
            when(userProfileDaoMock.get(any(UserId.class))).thenReturn(Optional.empty());

            assertThat(userProfileService.get(UserProfileFixtures.USER_ID)).usingRecursiveComparison()
                    .isEqualTo(Optional.empty());
        }

        @Test
        void applyCommand_updateProfile() {
            when(userProfileDaoMock.get(any(UserId.class)))
            .thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));

            when(commandProcessFactoryMock.getCommandProcessor(any(String.class)))
            .thenReturn(commandProcessorMock);

            when(commandProcessorMock.process(UserProfileFixtures.USER_PROFILE,UserProfileFixtures.USER_PROFILE.userProfileProperties()))
            .thenReturn(UserProfileFixtures.USER_PROFILE.userProfileProperties());


            userProfileService.applyCommand(UserProfileFixtures.USER_PROFILE.userId(), UserProfileFixtures.REPLACE_COMMAND_TYPE,
            anyMap());

            verify(userProfileDaoMock, times(1))
            .addUpdate(any(UserProfile.class));
        }

        @Test
        void applyCommand_updateProcessed() {
            when(userProfileDaoMock.get(any(UserId.class)))
            .thenReturn(Optional.of(UserProfileFixtures.USER_PROFILE));

            when(commandProcessFactoryMock.getCommandProcessor(any(String.class)))
            .thenReturn(commandProcessorMock);

            when(commandProcessorMock.process(UserProfileFixtures.USER_PROFILE,UserProfileFixtures.USER_PROFILE.userProfileProperties()))
            .thenReturn(UserProfileFixtures.USER_PROFILE.userProfileProperties());


            userProfileService.applyCommand(UserProfileFixtures.USER_PROFILE.userId(), UserProfileFixtures.REPLACE_COMMAND_TYPE,
            anyMap());

            verify(commandProcessorMock, times(1))
            .process(any(UserProfile.class),anyMap());
        }
    }
}