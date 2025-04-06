package de.rjst.cs.logic;

import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerEntity;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import de.rjst.cs.logic.mapper.CustomerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class UpdateCustomerFunction implements Function<CustomerDto, CustomerDto> {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public CustomerDto apply(final CustomerDto customerDto) {
        if (customerDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer ID is required");
        }
        customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        var customer = customerEntityMapper.apply(customerDto);
        customer = customerRepository.save(customer);
        return customerDtoMapper.apply(customer);
    }
}
