package de.rjst.cs.api.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface DeleteCustomer {

    @Operation(
            summary = "Deletes an existing customer",
            description = "Deletes an existing customer from the system"
    )
    @Parameter(
            name = "id",
            description = "The ID of the customer to be deleted",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "customer_deleted",
                            value = "1"
                    ),
                    @ExampleObject(
                            name = "non_customer",
                            value = "9999999999"
                    )
            })
    @ApiResponse(
            responseCode = "204",
            description = "Customer successfully deleted",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, examples = {
                    @ExampleObject(
                            name = "customer_deleted"
                    ),
                    @ExampleObject(
                            name = "non_customer"
                    )
            })
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    ResponseEntity<?> deleteCustomer(Long id);

}
