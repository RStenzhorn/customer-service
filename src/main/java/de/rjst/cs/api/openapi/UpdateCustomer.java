package de.rjst.cs.api.openapi;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.openapi.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ProblemDetail;

public interface UpdateCustomer {

    @Operation(
            summary = "Updates an existing customer",
            description = "Updates the data of an existing customer in the system"
    )
    @RequestBody(
            description = "The updated data of the customer",
            required = true,
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = {
                    @ExampleObject(
                            name = "customer_updated",
                            value = """
                                    {
                                      "id": 1,
                                      "firstName": "string",
                                      "lastName": "string",
                                      "birthDate": "2025-04-05",
                                      "email": "string"
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "customer_updated_notFound",
                            value = """
                                    {
                                      "id": 999999999,
                                      "firstName": "string",
                                      "lastName": "string",
                                      "birthDate": "2025-04-05",
                                      "email": "string"
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "customer_updated_invalid",
                            value = """
                                    {
                                      "id": 1,
                                      "firstName": "",
                                      "lastName": "string",
                                      "birthDate": "2025-04-05",
                                      "email": "string"
                                    }
                                    """
                    )
            })
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer successfully updated",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "customer_updated",
                    value = """
                            {
                              "id": {{ request.body/id }},
                              "firstName": "{{ request.body/firstName }}",
                              "lastName": "{{ request.body/lastName }}",
                              "birthDate": "{{ request.body/birthDate }}",
                              "email": "{{ request.body/email }}"
                            }
                            """
            ))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples =
            @ExampleObject(
                    name = "customer_updated_invalid"
            ))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Customer not found",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples =
            @ExampleObject(
                    name = "customer_updated_notFound"
            ))
    )
    CustomerDto updateCustomer(CustomerDto customerDto);

}
