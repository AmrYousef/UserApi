package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

public interface ICommandProcessFactory {
    ICommandProcessor getCommandProcessor(String command);

}
