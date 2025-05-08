package de.rjst.cs.api.openapi;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.openapi.model.CustomerDto;
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
            content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class)), examples = @ExampleObject(
                    name = "Example customer list",
                    value = """
                            [{
                                "id": 1,
                                "firstName": "string",
                                "lastName": "string",
                                "birthDate": "2025-04-05",
                                "email": "string"
                              }
                            ]
                            """
            ))
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error")
    List<CustomerDto> getCustomers();

}
