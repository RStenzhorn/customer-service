package de.rjst.cs.api.openapi.delete;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

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
                        name = Usecase.DELETE_CUSTOMER,
                            value = "1"
                    ),
                    @ExampleObject(
                        name = Usecase.CUSTOMER_ALREADY_DELETED,
                            value = "9999999999"
                    )
            })
    @ApiResponse(
            responseCode = "204",
            description = "Customer successfully deleted",
        content = @Content(mediaType = APPLICATION_JSON_VALUE,
            examples = {
                @ExampleObject(name = Usecase.DELETE_CUSTOMER),
                @ExampleObject(name = Usecase.CUSTOMER_ALREADY_DELETED)
            })
    )
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error"
    )
    ResponseEntity<?> deleteCustomer(Long id);

}
