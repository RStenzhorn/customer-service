package de.rjst.cs.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import de.rjst.cs.api.openapi.CreateCustomer;
import de.rjst.cs.api.openapi.DeleteCustomer;
import de.rjst.cs.api.openapi.GetCustomerById;
import de.rjst.cs.api.openapi.GetCustomers;
import de.rjst.cs.api.openapi.UpdateCustomer;
import de.rjst.cs.api.openapi.model.CreateCustomerDto;
import de.rjst.cs.api.openapi.model.CustomerDto;
import de.rjst.cs.logic.AllCustomerSupplier;
import de.rjst.cs.logic.CreateCustomerFunction;
import de.rjst.cs.logic.CustomerByIdFunction;
import de.rjst.cs.logic.DeleteCustomerFunction;
import de.rjst.cs.logic.UpdateCustomerFunction;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class CustomerController implements CreateCustomer, GetCustomers, GetCustomerById, UpdateCustomer, DeleteCustomer {

    private final AllCustomerSupplier allCustomerSupplier;
    private final CustomerByIdFunction customerByIdFunction;
    private final CreateCustomerFunction createCustomerFunction;
    private final UpdateCustomerFunction updateCustomerFunction;
    private final DeleteCustomerFunction deleteCustomerFunction;

    @GetMapping(CustomerEndpoints.CUSTOMERS)
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomers() {
        return allCustomerSupplier.get();
    }

    @GetMapping(CustomerEndpoints.CUSTOMER)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable final Long id) {
        return customerByIdFunction.apply(id);
    }

    @PostMapping(CustomerEndpoints.CUSTOMERS)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody final CreateCustomerDto customerDto) {
        return createCustomerFunction.apply(customerDto);
    }

    @PutMapping(CustomerEndpoints.CUSTOMERS)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@Valid @RequestBody final CustomerDto customerDto) {
        return updateCustomerFunction.apply(customerDto);
    }

    @DeleteMapping(CustomerEndpoints.CUSTOMER)
    public ResponseEntity<?> deleteCustomer(@PathVariable final Long id) {
        deleteCustomerFunction.accept(id);

        return ResponseEntity.noContent()
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();
    }
}
