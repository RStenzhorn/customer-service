package de.rjst.cs.api.openapi.get.all;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;

public interface GetCustomers {

    @Operation(
        summary = "Returns all customers",
        description = "Provides a list of all customers stored in the system"
    )
    @ApiResponse(
        responseCode = "200",
        description = "List was successfully retrieved",
        content = @Content(mediaType = APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class)),
            examples = @ExampleObject(
                name = "Example customer list",
                value = Response.SUCCESSFUL
            ))
    )
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error"
    )
    List<CustomerDto> getCustomers();

}
