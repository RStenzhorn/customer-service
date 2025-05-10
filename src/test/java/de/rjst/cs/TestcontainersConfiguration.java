package de.rjst.cs;

import io.github.microcks.testcontainers.MicrocksContainersEnsemble;
import java.util.List;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    private static final Network network = Network.newNetwork();

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest")).withDatabaseName("customer");
    }

    @Bean
    public MicrocksContainersEnsemble microcksEnsemble() {
        final var ensemble = new MicrocksContainersEnsemble(network,
                                                            "quay.io/microcks/microcks-uber:1.10.0")
            .withMainArtifacts("customer-service-openapi.yaml");
        final var microcksContainer = ensemble.getMicrocksContainer();
        microcksContainer.setPortBindings(List.of("8585:8080"));
        return ensemble;
    }

    @Bean
    public ApplicationListener<ApplicationStartedEvent> portExposer(final ConfigurableWebServerApplicationContext context) {
        return event -> {
            final var webServer = context.getWebServer();
            Testcontainers.exposeHostPorts(webServer.getPort());
        };
    }

}
