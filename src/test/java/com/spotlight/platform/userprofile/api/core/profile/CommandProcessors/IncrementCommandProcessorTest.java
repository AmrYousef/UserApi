package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import com.spotlight.platform.userprofile.api.core.logging.PhoenixLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class IncrementCommandProcessorTest {
    private final PhoenixLogger loggerMock = mock(PhoenixLogger.class);
    private final IncrementCommandProcessor commandProcessor = new IncrementCommandProcessor(loggerMock);

    @Nested
    @DisplayName("Process Increment Command Processor")
    class Get {
        @Test
        void forNoProfileAddNewProperties() {

            assertThat(commandProcessor.applyCommand(null,
            UserProfileFixtures.INCREMENT_COMMAND_PROPERTIES))
            .usingRecursiveComparison()
                    .isEqualTo(UserProfileFixtures.INCREMENT_MERGE_NULL_PROFILE);
        }

        @Test
        void forExistingProfileMergeProperties() {

            assertThat(commandProcessor.applyCommand(UserProfileFixtures.USER_PROFILE_INCREMENT,
            UserProfileFixtures.INCREMENT_COMMAND_PROPERTIES))
             .usingRecursiveComparison()
                    .isEqualTo(UserProfileFixtures.INCREMENT_MERGE_PROFILE);
        }
    }
}