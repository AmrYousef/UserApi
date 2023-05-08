package com.spotlight.platform.userprofile.api.core.logging;

public interface ILogger {

    void setContext(String name);

    void info(String info);

    void error(String error);

    void exception(Exception e);
    
}
