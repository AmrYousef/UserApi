package com.spotlight.platform.userprofile.api.core.profile.CommandProcessors;

import com.google.inject.Inject;
import com.spotlight.platform.userprofile.api.core.exceptions.business.InvalidCommandException;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.core.profile.CommandType;

public class CommandProcessFactory implements ICommandProcessFactory{
    private ILogger logger;

    @Inject
    public CommandProcessFactory(ILogger logger) {
        this.logger = logger;
    }

    public ICommandProcessor getCommandProcessor(String command){
        if(CommandType.Replace.equalsName(command))
            return new ReplaceCommandProcessor(logger);
        else if(CommandType.Increment.equalsName(command))
            return new IncrementCommandProcessor(logger);
        else if(CommandType.Collect.equalsName(command))
            return new CollectCommandProcessor(logger);
        else throw new InvalidCommandException();
    }    
}
