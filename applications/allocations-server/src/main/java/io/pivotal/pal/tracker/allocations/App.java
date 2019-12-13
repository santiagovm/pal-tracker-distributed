package io.pivotal.pal.tracker.allocations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestOperations;

import java.util.TimeZone;

@EnableWebSecurity
@EnableResourceServer
@EnableOAuth2Client
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"io.pivotal.pal.tracker.allocations", "io.pivotal.pal.tracker.restsupport"})
public class App {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProjectClient projectClient(
        RestOperations restOperations,
        @Value("${registration.server.endpoint}") String registrationEndpoint
    ) {
        return new ProjectClient(restOperations, registrationEndpoint);
    }
}


// todo: next step is to continue with SSO service setup
// https://waveland.education.pivotal.io/cnd-course/cloud-native-developer/spring-cloud-developer/security/index.html#run-locally
// have a blocker, SSO service not available in my PWS marketplace
// opened a ticket with PWS, also Cole connected me with Claire and Andrew via Slack
// at a dead end at this point

// consider using Galvin's workaround,
// deploy my own OAuth Server
// https://github.com/gshaw-pivotal/pal-tracker-distributed/commits/master
