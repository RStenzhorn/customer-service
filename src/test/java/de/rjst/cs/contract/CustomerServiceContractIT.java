package de.rjst.cs.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.rjst.cs.BaseIntegrationTest;
import de.rjst.cs.CustomerController;
import de.rjst.cs.api.CreateCustomerDto;
import io.github.microcks.testcontainers.model.TestRequest;
import io.github.microcks.testcontainers.model.TestRunnerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceContractIT extends BaseIntegrationTest {

    @Autowired
    private CustomerController customerController;


    @Test
    void testOpenAPIContract() throws Exception {
        var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setBirthDate(LocalDate.now());
        createCustomerDto.setFirstName("string");
        createCustomerDto.setLastName("string");
        createCustomerDto.setEmail("string");
        var customer = customerController.createCustomer(createCustomerDto);
        System.out.println(customer);

        var testRequest = new TestRequest.Builder()
                .serviceId("Customer Service API:v1")
                .runnerType(TestRunnerType.OPEN_API_SCHEMA.name())
                .testEndpoint("http://host.docker.internal:" + port)
                .build();

        var testResult = microcksEnsemble.getMicrocksContainer().testEndpoint(testRequest);

        // You may inspect complete response object with following:
        var mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testResult));

        assertTrue(testResult.isSuccess());
        assertEquals(5, testResult.getTestCaseResults().size());
    }
}
