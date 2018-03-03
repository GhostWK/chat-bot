package ru.frtk.das.resources;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/health-check")
public class HealthCheckResource {

    @GET
    public boolean healthCheck() {
        return true;
    }
}
