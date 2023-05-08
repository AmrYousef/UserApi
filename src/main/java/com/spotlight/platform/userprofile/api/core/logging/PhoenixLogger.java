package com.spotlight.platform.userprofile.api.core.logging;

import java.util.logging.Logger;

public final class PhoenixLogger implements ILogger {
    //Simple logging, in other case we might need more proper logging mechanism and framework
    Logger logger = Logger.getLogger(PhoenixLogger.class.getName());    

    @Override
    public void setContext(String name) {
        this.logger = Logger.getLogger(name);
        info("Update context to %s".formatted(name));
    }

    @Override
    public void info(String info) {
        logger.info(info);
    }

    @Override
    public void error(String error) {
        // logger.log(Level.SEVERE,error);
    }

    @Override
    public void exception(Exception e) {
        // logger.log(Level.SEVERE,e.getMessage(),e);
    }
    
}
