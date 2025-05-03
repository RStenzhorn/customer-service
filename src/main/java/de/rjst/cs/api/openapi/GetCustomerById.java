package de.rjst.cs.api.openapi;

import de.rjst.cs.api.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface GetCustomerById {

    @Operation(summary = "Retrieves a customer")
    @Parameter(
            name = "id",
            description = "The ID of the customer",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "customer_found",
                            value = "1"
                    ),
                    @ExampleObject(
                            name = "customer_not_found",
                            value = "9999999999"
                    )
            })
    @ApiResponse(
            responseCode = "200",
            description = "Customer found",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "customer_found",
                    value = """
                            {
                              "id": 1,
                              "firstName": "string",
                              "lastName": "string",
                              "birthDate": "2025-04-05",
                              "email": "string"
                            }
                            """
            ))
    )
    @ApiResponse(responseCode = "404", description = "Customer not found",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples = @ExampleObject(
                    name = "customer_not_found"
            ))
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    CustomerDto getCustomerById(Long id);
}
