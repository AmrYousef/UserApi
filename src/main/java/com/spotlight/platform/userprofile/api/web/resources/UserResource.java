package com.spotlight.platform.userprofile.api.web.resources;

import com.spotlight.platform.userprofile.api.core.exceptions.business.EntityNotFoundException;
import com.spotlight.platform.userprofile.api.core.logging.ILogger;
import com.spotlight.platform.userprofile.api.core.profile.UserProfileService;
import com.spotlight.platform.userprofile.api.model.common.Command;
import com.spotlight.platform.userprofile.api.model.profile.UserProfile;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserId;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserProfileService userProfileService;
    private final ILogger logger;

    @Inject
    public UserResource(UserProfileService userProfileService, ILogger logger) {
        logger.setContext(UserResource.class.getName());
        this.logger = logger;
        logger.info("start initializing Users service");
        this.userProfileService = userProfileService;
        logger.info("Users service Initialized successfully");
    }

    @Path("/{userId}/profile")
    @GET
    public UserProfile getUserProfile(@Valid @PathParam("userId") UserId userId) {
        logger.info("start get user profile by id");
        try
        {
            return userProfileService.get(userId)
                    .orElseThrow(EntityNotFoundException::new);
        }
        catch(EntityNotFoundException e)
        {
            logger.exception(e);
            throw e;
        }
    }

    @POST
    @Path("/")
    public Response applyCommand(@Valid List<Command> commands)
    {
        try
        {
            for (var command : commands)
            {
                logger.info("start apply user profile command");
                logger.info("UserId %s".formatted(command.userId()));
                userProfileService.applyCommand(UserId.valueOf(command.userId()),command.type(),command.properties());
                logger.info("apply user profile command completed");
            }
            return Response.ok()
            .build();
        }
        catch(Exception e)
        {
            logger.exception(e);
            throw e;
        }
    }
}
