package de.rjst.cs.api;

import de.rjst.cs.CustomerController;
import de.rjst.cs.TestcontainersConfiguration;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;
import org.openapitools.client.api.CustomerControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

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
        createCustomerDto.setFirstName("anyFirstName");
        createCustomerDto.setLastName("anyLastName");
        createCustomerDto.setEmail(null);
        customerController.createCustomer(createCustomerDto);

        customerControllerApi.getCustomerById()
                .idPath(1L)
                .respSpec((spec) -> spec
                        .log(LogDetail.ALL)
                        .expectStatusCode(HttpStatus.OK.value())
                        .expectBody("id", equalTo(1))
                        .expectBody("firstName", equalTo("anyFirstName"))
                        .expectBody("lastName", equalTo("anyLastName"))
                        .expectBody("birthDate", equalTo(LocalDate.now().toString()))
                        .expectBody("email", nullValue())
                );
    }

}
