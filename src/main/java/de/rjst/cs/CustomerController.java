package de.rjst.cs;

import de.rjst.cs.api.CreateCustomerDto;
import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.api.DeleteResult;
import de.rjst.cs.logic.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;


@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final AllCustomerSupplier allCustomerSupplier;
    private final CustomerByIdFunction customerByIdFunction;
    private final CreateCustomerFunction createCustomerFunction;
    private final UpdateCustomerFunction updateCustomerFunction;
    private final DeleteCustomerFunction deleteCustomerFunction;


    @Operation(
            summary = "Gibt alle Kunden zurück",
            description = "Liefert eine Liste aller im System gespeicherten Kunden",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste wurde erfolgreich abgerufen",
                            content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class)), examples = @ExampleObject(
                                    name = "Beispiel-Kundenliste",
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Keine Kunden gefunden"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Interner Serverfehler")
            }
    )

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomers() {
        return allCustomerSupplier.get();
    }

    @Operation(summary = "Ruft einen Kunden ab")
    @Parameter(
            name = "id",
            description = "Die ID des Kunden",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "kunden_gefunden",
                            value = "1"
                    ),
                    @ExampleObject(
                            name = "kunden_nicht_gefunden",
                            value = "9999999999"
                    )
            })
    @ApiResponse(
            responseCode = "200",
            description = "Kunde gefunden",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "kunden_gefunden",
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
    @ApiResponse(responseCode = "404", description = "Kunde nicht gefunden",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples = @ExampleObject(
                    name = "kunden_nicht_gefunden"
            )))
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable final Long id) {
        return customerByIdFunction.apply(id);
    }

    @Operation(
            summary = "Erstellt einen neuen Kunden",
            description = "Erstellt einen neuen Kunden im System"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Die Daten des zu erstellenden Kunden",
            required = true,
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateCustomerDto.class), examples = {
                    @ExampleObject(
                            name = "kunden_erstellt",
                            value = """
                                    {
                                      "firstName": "string",
                                      "lastName": "string",
                                      "birthDate": "2025-04-05",
                                      "email": "string"
                                    }
                                    """
                    ),
                    @ExampleObject(
                            name = "kunden_erstellt_invalid",
                            value = """
                                    {
                                      "firstName": "string",
                                      "lastName": "",
                                      "birthDate": "2025-04-05",
                                      "email": null
                                    }
                                    """
                    )
            })
    )
    @ApiResponse(
            responseCode = "201",
            description = "Kunde erfolgreich erstellt",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "kunden_erstellt",
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
    @ApiResponse(responseCode = "400", description = "Ungültige Eingabedaten",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples = @ExampleObject(
                    name = "kunden_erstellt_invalid"
            )))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody final CreateCustomerDto customerDto) {
        return createCustomerFunction.apply(customerDto);
    }

    @Operation(
            summary = "Aktualisiert einen bestehenden Kunden",
            description = "Aktualisiert die Daten eines bestehenden Kunden im System"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Die aktualisierten Daten des Kunden",
            required = true,
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "kunden_aktualisiert",
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
    @ApiResponse(
            responseCode = "200",
            description = "Kunde erfolgreich aktualisiert",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerDto.class), examples = @ExampleObject(
                    name = "kunden_aktualisiert",
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
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@Valid @RequestBody final CustomerDto customerDto) {
        return updateCustomerFunction.apply(customerDto);
    }

    @Operation(
            summary = "Löscht einen bestehenden Kunden",
            description = "Löscht einen bestehenden Kunden aus dem System"
    )
    @Parameter(
            name = "id",
            description = "Die ID des zu löschenden Kunden",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "kunden_geloescht",
                            value = "1"
                    ),
                    @ExampleObject(
                            name = "kunden_nicht_geloescht",
                            value = "9999999999"
                    )
    })
    @ApiResponse(
            responseCode = "200",
            description = "Kunde erfolgreich gelöscht",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = DeleteResult.class), examples =
            @ExampleObject(
                    name = "kunden_geloescht"
            ))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Kunde nicht gefunden",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class), examples =
            @ExampleObject(
                    name = "kunden_nicht_geloescht"
            ))
    )
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteResult deleteCustomer(@PathVariable final Long id) {
        deleteCustomerFunction.accept(id);
        return DeleteResult.builder().id(id).build();
    }


}
