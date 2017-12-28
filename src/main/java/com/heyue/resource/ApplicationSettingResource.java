package com.heyue.resource;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("api")
public class ApplicationSettingResource {

    @GET
    @Path("/application")
    @Produces(MediaType.APPLICATION_JSON)
    public Mono<ServerResponse> getApplication(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.just("hello"), String.class);
    }
}
