package de.rjst.cs.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.rjst.cs.TestcontainersConfiguration;
import io.github.microcks.testcontainers.MicrocksContainersEnsemble;
import io.github.microcks.testcontainers.model.TestRequest;
import io.github.microcks.testcontainers.model.TestRunnerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class CustomerServiceContractIT {

    @Autowired
    private MicrocksContainersEnsemble microcksEnsemble;

    @Autowired
    private ConfigurableWebServerApplicationContext context;


    @Test
    void testOpenAPIContract() throws Exception {
        final var webServer = context.getWebServer();
        final var port = webServer.getPort();
        final var testRequest = new TestRequest.Builder()
                .serviceId("Customer Service API:v1")
                .runnerType(TestRunnerType.OPEN_API_SCHEMA.name())
                .testEndpoint("http://host.docker.internal:" + port)
                .build();

        final var microcksContainer = microcksEnsemble.getMicrocksContainer();
        final var testResult = microcksContainer.testEndpoint(testRequest);

        final var mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testResult));

        assertTrue(testResult.isSuccess());
        assertEquals(5, testResult.getTestCaseResults().size());
    }
}
