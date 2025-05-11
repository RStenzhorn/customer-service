package de.rjst.cs.api.openapi.update;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.model.CustomerDto;
import de.rjst.cs.api.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ProblemDetail;

public interface UpdateCustomer {

    @Operation(
        summary = "Updates an existing customer",
        description = "Updates the data of an existing customer in the system",
        extensions = @Extension(
            name = "x-microcks-operation",
            properties = {
                @ExtensionProperty(name = "delay", value = "25"),
                @ExtensionProperty(name = "dispatcher", value = "SCRIPT"),
                @ExtensionProperty(
                    name = "dispatcherRules",
                    value = """
                        def result = "updateCustomer";
                        
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
        description = "The updated data of the customer",
        required = true,
        content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = {
            @ExampleObject(
                name = Usecase.UPDATE_CUSTOMER,
                value = Request.SUCCESSFUL
            ),
            @ExampleObject(
                name = Usecase.CUSTOMER_NOT_FOUND,
                value = Request.CUSTOMER_NOT_FOUND
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
            )
        })
    )
    @ApiResponse(
        responseCode = "200",
        description = "Customer successfully updated",
        content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
            name = Usecase.UPDATE_CUSTOMER,
            value = Response.SUCCESSFUL
        ))
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid input data",
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponse.class),
            examples = {
                @ExampleObject(
                    name = Usecase.INVALID_FIRST_NAME,
                    value = Response.INVALID_FIRST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_LAST_NAME,
                    value = Response.INVALID_LAST_NAME
                ),
                @ExampleObject(
                    name = Usecase.INVALID_BIRTH_DATE,
                    value = Response.INVALID_BIRTH_DATE
                )
            }
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "Customer not found",
        content = @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ProblemDetail.class),
            examples = @ExampleObject(name = Usecase.CUSTOMER_NOT_FOUND)
        )
    )
    CustomerDto updateCustomer(CustomerDto customerDto);

}
