package com.heyue;

import com.heyue.resource.ApplicationSettingResource;
import com.heyue.service.EchoHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by jessepi on 4/9/16.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EchoHandler echoHandler) {
        return route(GET("/echo"), echoHandler::echo);
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterResource(ApplicationSettingResource applicationSettingResource) {
        return route(GET("/api/{eventId}"), applicationSettingResource::getApplication);
    }
}
