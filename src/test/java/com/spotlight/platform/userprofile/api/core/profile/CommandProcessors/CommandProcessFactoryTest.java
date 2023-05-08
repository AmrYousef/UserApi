package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;
import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidCommandException;
import com.spotlight.platform.userprofile.api.core.logging.PhoenixLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class CommandProcessFactoryTest {
    private final PhoenixLogger loggerMock = mock(PhoenixLogger.class);
    private final CommandProcessFactory commandProcessorFactory = new CommandProcessFactory(loggerMock);

    @Nested
    @DisplayName("Command Processor Factory")
    class Get {
        @Test
        void getReplaceCommandProcessorOnReplaceType() {

            assertThat(commandProcessorFactory.getCommandProcessor(UserProfileFixtures.REPLACE_COMMAND_TYPE))
                    .isInstanceOf(ReplaceCommandProcessor.class);
        }

        @Test
        void getIncrementCommandProcessorOnIncrementType() {

            assertThat(commandProcessorFactory.getCommandProcessor(UserProfileFixtures.INCREMENT_COMMAND_TYPE))
                    .isInstanceOf(IncrementCommandProcessor.class);
        }

        @Test
        void getCollectiCommandProcessorOnCollectType() {

            assertThat(commandProcessorFactory.getCommandProcessor(UserProfileFixtures.COLLECT_COMMAND_TYPE))
                    .isInstanceOf(CollectCommandProcessor.class);
        }

        @Test
        void getCommandOnWrongType_throwsException() {
            assertThatThrownBy(()-> commandProcessorFactory.getCommandProcessor(UserProfileFixtures.WRONG_COMMAND_TYPE))
            .isExactlyInstanceOf(InvalidCommandException.class);
        }
    }
}