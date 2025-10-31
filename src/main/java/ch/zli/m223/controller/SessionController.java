package ch.zli.m223.controller;

import ch.zli.m223.dto.Credentials;
import ch.zli.m223.service.SessionService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/session")
@PermitAll
public class SessionController {

    @Inject
    SessionService sessionService;

    @POST
    public Response create(Credentials credentials) {
        return sessionService.createSession(credentials.getUsername(), credentials.getPassword());
    }
}
