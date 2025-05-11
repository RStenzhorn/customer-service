package de.rjst.cs.datasource;

import static io.restassured.RestAssured.given;

import de.rjst.cs.api.CustomerEndpoints;
import de.rjst.cs.api.model.CreateCustomerDto;
import de.rjst.cs.api.model.CustomerDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerControllerRestAssured {

    private static final String ID = "id";

    private final RequestSpecification requestSpecification;
    private final ResponseSpecification responseSpecification;

    public ExtractableResponse<Response> getCustomers() {
        return given()
                .spec(requestSpecification)
                .get(CustomerEndpoints.CUSTOMERS)
                .then()
                .spec(responseSpecification)
                .extract();
    }

    public ExtractableResponse<Response> getCustomerById(final Long id) {
        return given()
                .spec(requestSpecification)
                .pathParams(ID, id)
                .get(CustomerEndpoints.CUSTOMER)
                .then()
                .spec(responseSpecification)
                .extract();
    }

    public ExtractableResponse<Response> postCustomers(final CreateCustomerDto createCustomerDto) {
        return given()
                .spec(requestSpecification)
                .body(createCustomerDto)
                .post(CustomerEndpoints.CUSTOMERS)
                .then()
                .spec(responseSpecification)
                .extract();
    }


    public ExtractableResponse<Response> putCustomer(final CustomerDto customerDto) {
        return given()
                .spec(requestSpecification)
                .body(customerDto)
                .put(CustomerEndpoints.CUSTOMERS)
                .then()
                .spec(responseSpecification)
                .extract();
    }

    public ExtractableResponse<Response> deleteCustomer(final Long id) {
        return given()
                .spec(requestSpecification)
                .pathParams(ID, id)
                .delete(CustomerEndpoints.CUSTOMER)
                .then()
                .spec(responseSpecification)
                .extract();
    }


}
