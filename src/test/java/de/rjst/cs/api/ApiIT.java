package de.rjst.cs.api;

import de.rjst.cs.CustomerController;
import de.rjst.cs.TestcontainersConfiguration;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.CustomerControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class ApiIT {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerControllerApi customerControllerApi;

    @Test
    void testApiClient() {
        final var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setBirthDate(LocalDate.now());
        createCustomerDto.setFirstName("string");
        createCustomerDto.setLastName("string");
        createCustomerDto.setEmail(null);
        customerController.createCustomer(createCustomerDto);

        customerControllerApi.getCustomerById()
                .idPath(1L)
                .respSpec(response -> response.log(LogDetail.ALL))
                .executeAs(response -> response);
    }

}
