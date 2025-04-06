package de.rjst.cs.logic;

import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CustomerByIdFunction implements Function<Long, CustomerDto> {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public CustomerDto apply(final Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerDtoMapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
