package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.spotlight.platform.userprofile.api.core.exceptions.business.BaseBusinessException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
class BaseBusinessExceptionMapperTest {

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new MockResource())
            .setRegisterDefaultExceptionMappers(false)
            .addProvider(new BaseBusinessExceptionMapper())
            .build();

    private Client client;

    @BeforeEach
    void setUp() {
        client = EXT.client();
    }

    @Test
    void BaseBusiness_ResultsIn404() {
        Response response = client.target(MockResource.RESOURCE_URLS.THROW_EXCEPTION).request().post(Entity.json("{}"));

        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Path("/")
    public static class MockResource {
        public static class RESOURCE_URLS {
            public static final String THROW_EXCEPTION = "/throwBaseBusinessException";
        }

        @POST
        @Path(RESOURCE_URLS.THROW_EXCEPTION)
        public void throwException() {
            throw new BaseBusinessException("msg");
        }
    }
}