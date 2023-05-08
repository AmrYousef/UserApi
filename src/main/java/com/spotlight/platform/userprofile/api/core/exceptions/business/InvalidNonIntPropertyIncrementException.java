package com.spotlight.platform.userprofile.api.core.exceptions.business;


public class InvalidNonIntPropertyIncrementException extends BaseBusinessException {
    public InvalidNonIntPropertyIncrementException() {
        super("Cannot Increment Non-Integer property");
    }
    public InvalidNonIntPropertyIncrementException(String message) {
        super(message);
    }
}
