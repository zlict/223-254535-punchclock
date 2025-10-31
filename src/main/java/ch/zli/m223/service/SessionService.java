package ch.zli.m223.service;

import java.util.Arrays;
import java.util.HashSet;

import ch.zli.m223.model.ApplicationUser;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SessionService {

    @Inject
    private EntityManager entityManager;

    public Response createSession(String username, String password) {
        try {
            var user = findApplicationUserByUsername(username);
            String token = Jwt.upn(user.getUsername()).issuer("https://example.com/issuer")
                    .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                    .sign();

            return Response
                    .status(Response.Status.CREATED)
                    .header("Authorization", "Bearer ".concat(token))
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private ApplicationUser findApplicationUserByUsername(String username) {
        return entityManager.createNamedQuery("findByUsername", ApplicationUser.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
