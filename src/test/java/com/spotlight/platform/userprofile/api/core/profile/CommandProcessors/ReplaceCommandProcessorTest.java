package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import com.spotlight.platform.userprofile.api.core.logging.PhoenixLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ReplaceCommandProcessorTest {
    private final PhoenixLogger loggerMock = mock(PhoenixLogger.class);
    private final ReplaceCommandProcessor commandProcessor = new ReplaceCommandProcessor(loggerMock);

    @Nested
    @DisplayName("Process Replace Command Processor")
    class Get {
        @Test
        void returnUpdatedPropertiesOnly() {

            assertThat(commandProcessor.applyCommand(null,
            UserProfileFixtures.INCREMENT_COMMAND_PROPERTIES))
            .usingRecursiveComparison()
                    .isEqualTo(UserProfileFixtures.INCREMENT_COMMAND_PROPERTIES);
        }
    }
}