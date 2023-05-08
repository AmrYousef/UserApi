package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.spotlight.platform.userprofile.api.core.exceptions.business.EntityNotFoundException;

public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
