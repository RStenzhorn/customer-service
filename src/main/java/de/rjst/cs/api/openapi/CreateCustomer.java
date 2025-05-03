package de.rjst.cs.api.openapi;

import de.rjst.cs.api.CreateCustomerDto;
import de.rjst.cs.api.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ProblemDetail;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface CreateCustomer {

    @Operation(
            summary = "Creates a new customer",
            description = "Creates a new customer in the system"
    )
    @RequestBody(
            description = "The data of the customer to be created",
            required = true,
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateCustomerDto.class), examples = {
                    @ExampleObject(
                            name = "customer_created",
                            value = """
                                    {
                                      "firstName": "Max",
                                      "lastName": "Mustermann",
                                      "birthDate": "2025-04-05",
                                      "email": "max.mustermann@web.de"
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "customer_created_invalid_firstname",
                            value = """
                                    {
                                      "firstName": "",
                                      "lastName": "Mustermann",
                                      "birthDate": "2025-04-05",
                                      "email": null
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "customer_created_invalid_lastname",
                            value = """
                                    {
                                      "firstName": "string",
                                      "lastName": "",
                                      "birthDate": "2025-04-05",
                                      "email": null
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "customer_created_invalid_birthDate",
                            value = """
                                    {
                                      "firstName": "string",
                                      "lastName": "",
                                      "birthDate": "2025-04-05",
                                      "email": null
                                    }
                                    """
                    ),
            })
    )
    @ApiResponse(
            responseCode = "201",
            description = "Customer successfully created",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "customer_created",
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
    @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples = {
                    @ExampleObject(name = "customer_created_invalid_firstname"),
                    @ExampleObject(name = "customer_created_invalid_lastname"),
                    @ExampleObject(name = "customer_created_invalid_birthDate"),
            })
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    CustomerDto createCustomer(CreateCustomerDto customerDto);

}
