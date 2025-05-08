package de.rjst.cs.api.openapi;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.openapi.model.CreateCustomerDto;
import de.rjst.cs.api.openapi.model.CustomerDto;
import de.rjst.cs.api.openapi.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
        content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class), examples = {
            @ExampleObject(
                name = "customer_created_invalid_firstname",
                description = "Customer firstname is invalid",
                value = """
                    {
                      "message": "Validierungsfehler",
                      "errors": {
                        "firstName": "Vorname darf nicht leer sein"
                      }
                    }
                    """
            ),
            @ExampleObject(
                name = "customer_created_invalid_lastname",
                description = "Customer lastname is invalid",
                value = """
                    {
                      "message": "Validierungsfehler",
                      "errors": {
                        "lastName": "Nachname darf nicht leer sein"
                      }
                    }
                    """
            ),
            @ExampleObject(
                name = "customer_created_invalid_birthDate",
                description = "Customer birthDate is invalid",
                value = """
                    {
                      "message": "Validierungsfehler",
                      "errors": {
                        "birthDate": "Geburtsdatum ist ungültig"
                      }
                    }
                    """
            ),
        })
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    CustomerDto createCustomer(CreateCustomerDto customerDto);

}
