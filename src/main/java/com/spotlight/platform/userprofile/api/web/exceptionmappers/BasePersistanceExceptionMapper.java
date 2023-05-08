package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.spotlight.platform.userprofile.api.core.exceptions.persistance.BasePersistanceException;

public class BasePersistanceExceptionMapper implements ExceptionMapper<BasePersistanceException> {
    @Override
    public Response toResponse(BasePersistanceException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(exception.getMessage())
        .build();
    }
}
