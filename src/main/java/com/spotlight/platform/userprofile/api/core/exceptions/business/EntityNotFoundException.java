package com.spotlight.platform.userprofile.api.core.exceptions.business;

public class EntityNotFoundException extends BaseBusinessException {
    public EntityNotFoundException() {
        super("Entity not found");
    }
}
