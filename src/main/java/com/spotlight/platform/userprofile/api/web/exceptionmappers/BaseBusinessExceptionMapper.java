package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.spotlight.platform.userprofile.api.core.exceptions.business.BaseBusinessException;

public class BaseBusinessExceptionMapper implements ExceptionMapper<BaseBusinessException> {
    @Override
    public Response toResponse(BaseBusinessException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
        .entity(exception.getMessage())
        .build();
    }
}
