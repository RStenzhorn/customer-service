package de.rjst.cs.api.openapi.get.id;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.model.CustomerDto;
import de.rjst.cs.api.model.DefaultErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface GetCustomerById {

    @Operation(summary = "Retrieves a customer")
    @Parameter(
        name = "id",
        description = "The ID of the customer",
        required = true,
        examples = {
            @ExampleObject(
                name = Usecase.GET_CUSTOMER_BY_ID,
                value = "1"
            ),
            @ExampleObject(
                name = Usecase.CUSTOMER_NOT_FOUND,
                value = "9999999999"
            )
        }
    )
    @ApiResponse(
        responseCode = "200",
        description = "Customer found",
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CustomerDto.class),
            examples =
            @ExampleObject(
                name = Usecase.GET_CUSTOMER_BY_ID,
                value = Response.SUCCESSFUL
            )
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "Customer not found",
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = DefaultErrorResponse.class),
            examples = @ExampleObject(
                name = Usecase.CUSTOMER_NOT_FOUND,
                value = Response.CUSTOMER_NOT_FOUND
            )
        )
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    CustomerDto getCustomerById(Long id);
}
