package com.spotlight.platform.userprofile.api.core.exceptions.persistance;

public class InvalidUserProfileException extends BasePersistanceException {
    public InvalidUserProfileException() {
        super("Invalid User Profile");
    }
    public InvalidUserProfileException(String message) {
        super(message);
    }
}
