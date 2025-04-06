package de.rjst.cs.logic;

import de.rjst.cs.api.CreateCustomerDto;
import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import de.rjst.cs.logic.mapper.CustomerNewEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CreateCustomerFunction implements Function<CreateCustomerDto, CustomerDto> {

    private final CustomerRepository customerRepository;
    private final CustomerNewEntityMapper customerNewEntityMapper;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public CustomerDto apply(final CreateCustomerDto createCustomerDto) {
        final var customerEntity = customerNewEntityMapper.apply(createCustomerDto);
        final var savedCustomerEntity = customerRepository.save(customerEntity);
        return customerDtoMapper.apply(savedCustomerEntity);
    }
}
