package de.rjst.cs.api;

import de.rjst.cs.TestcontainersConfiguration;
import de.rjst.cs.datasource.CustomerControllerRestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class CustomerControllerIT {

    @Autowired
    private CustomerControllerRestAssured customerController;

    @Test
    void getCustomers() {
        final var response = customerController.getCustomers();
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void getCustomerById() {
        final ExtractableResponse<Response> response = customerController.getCustomerById(1L);
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void getCustomerById_notFound() {
        final ExtractableResponse<Response> response = customerController.getCustomerById(Long.MAX_VALUE);
        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    void createCustomer() {
        final var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setFirstName("John");
        createCustomerDto.setLastName("Doe");
        createCustomerDto.setBirthDate(LocalDate.of(1990, 1, 1));
        createCustomerDto.setEmail("<EMAIL>");

        final var response = customerController.postCustomers(createCustomerDto);
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @Test
    void createCustomer_badRequest() {
        final var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setFirstName("");
        createCustomerDto.setLastName("Doe");
        createCustomerDto.setBirthDate(LocalDate.of(1990, 1, 1));
        createCustomerDto.setEmail("<EMAIL>");

        final var response = customerController.postCustomers(createCustomerDto);
        assertThat(response.statusCode()).isEqualTo(400);
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}
