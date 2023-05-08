package com.spotlight.platform.userprofile.api.core.exceptions.business;

public class InvalidCommandException extends BaseBusinessException {
    public InvalidCommandException() {
        super("Invalid Command");
    }
    public InvalidCommandException(String message) {
        super(message);
    }
}
