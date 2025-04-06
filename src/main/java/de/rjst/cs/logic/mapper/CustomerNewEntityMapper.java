package de.rjst.cs.logic.mapper;

import de.rjst.cs.api.CreateCustomerDto;
import de.rjst.cs.database.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerNewEntityMapper implements Function<CreateCustomerDto, CustomerEntity> {

    @Override
    public CustomerEntity apply(final CreateCustomerDto createCustomerDto) {
        final var result = new CustomerEntity();
        result.setFirstName(createCustomerDto.getFirstName());
        result.setLastName(createCustomerDto.getLastName());
        result.setBirthDate(createCustomerDto.getBirthDate());
        result.setEmail(createCustomerDto.getEmail());
        return result;
    }
}
