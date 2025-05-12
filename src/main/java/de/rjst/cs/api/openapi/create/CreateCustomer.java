package de.rjst.cs.api.openapi.create;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.model.CreateCustomerDto;
import de.rjst.cs.api.model.CustomerDto;
import de.rjst.cs.api.model.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CreateCustomer {

    @Operation(
        summary = "Creates a new customer",
        description = "Creates a new customer in the system",
        extensions = @Extension(
            name = "x-microcks-operation",
            properties = {
                @ExtensionProperty(name = "delay", value = "25"),
                @ExtensionProperty(name = "dispatcher", value = "SCRIPT"),
                @ExtensionProperty(
                    name = "dispatcherRules",
                    value = """
                        def result = "createCustomer";
                        
                        def customer = new groovy.json.JsonSlurper().parseText(mockRequest.requestContent);
                        if (customer.firstName == null || customer.firstName.trim() == "") {
                          result = "invalidFirstName";
                        }
                        
                        if (customer.lastName == null || customer.lastName.trim() == "") {
                          result = "invalidLastName";
                        }
                        
                        if (customer.birthDate == null || customer.birthDate.trim() == "") {
                          result = "invalidBirthDate";
                        }
                        
                        return result;
                        """
                )
            }
        )
    )
    @RequestBody(
        description = "The data of the customer to be created",
        required = true,
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CreateCustomerDto.class),
            examples = {
                @ExampleObject(
                    name = Usecase.CREATE_CUSTOMER,
                    value = Request.SUCCESSFUL
                ),
                @ExampleObject(
                    name = Usecase.INVALID_FIRST_NAME,
                    value = Request.INVALID_FIRST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_LAST_NAME,
                    value = Request.INVALID_LAST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_BIRTH_DATE,
                    value = Request.INVALID_BIRTH_DATE
                ),
            }
        )
    )
    @ApiResponse(
        responseCode = "201",
        description = "Customer successfully created",
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = CustomerDto.class),
            examples = @ExampleObject(
                name = Usecase.CREATE_CUSTOMER,
                value = Response.SUCCESSFUL
            )
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid input data",
        content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ValidationErrorResponse.class), examples =
            {
                @ExampleObject(
                    name = Usecase.INVALID_FIRST_NAME,
                    description = "Firstname is invalid",
                    value = Response.INVALID_FIRST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_LAST_NAME,
                    description = "Lastname is invalid",
                    value = Response.INVALID_LAST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_BIRTH_DATE,
                    description = "Birthdate is invalid",
                    value = Response.INVALID_BIRTH_DATE
                ),
            }
        )
    )
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    CustomerDto createCustomer(CreateCustomerDto customerDto);

}
