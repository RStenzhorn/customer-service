package de.rjst.cs.logic;

import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class AllCustomerSupplier implements Supplier<List<CustomerDto>> {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public List<CustomerDto> get() {
        final var result = customerRepository.findAll()
                .stream()
                .map(customerDtoMapper)
                .toList();
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers found");
        }
        return result;

    }
}
