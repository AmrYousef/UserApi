package com.spotlight.platform.userprofile.api.core.exceptions.business;


public class InvalidIncrementValueException extends BaseBusinessException {
    public InvalidIncrementValueException() {
        super("Invalid Increment Value, All increment properties must be of nteger value");
    }
    public InvalidIncrementValueException(String message) {
        super(message);
    }
}
