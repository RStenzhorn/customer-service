package de.rjst.cs.api;

import de.rjst.cs.api.openapi.*;
import de.rjst.cs.logic.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController implements CreateCustomer, GetCustomers, GetCustomerById, UpdateCustomer, DeleteCustomer {

    private final AllCustomerSupplier allCustomerSupplier;
    private final CustomerByIdFunction customerByIdFunction;
    private final CreateCustomerFunction createCustomerFunction;
    private final UpdateCustomerFunction updateCustomerFunction;
    private final DeleteCustomerFunction deleteCustomerFunction;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomers() {
        return allCustomerSupplier.get();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable final Long id) {
        return customerByIdFunction.apply(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody final CreateCustomerDto customerDto) {
        return createCustomerFunction.apply(customerDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@Valid @RequestBody final CustomerDto customerDto) {
        return updateCustomerFunction.apply(customerDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable final Long id) {
        deleteCustomerFunction.accept(id);

        return ResponseEntity.noContent()
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }
}
