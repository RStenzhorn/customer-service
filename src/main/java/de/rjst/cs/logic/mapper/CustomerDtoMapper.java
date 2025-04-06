package de.rjst.cs.logic.mapper;

import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<CustomerEntity, CustomerDto> {

    @Override
    public CustomerDto apply(final CustomerEntity customerEntity) {
        final var result = new CustomerDto();
        result.setId(customerEntity.getId());
        result.setFirstName(customerEntity.getFirstName());
        result.setLastName(customerEntity.getLastName());
        result.setBirthDate(customerEntity.getBirthDate());
        result.setEmail(customerEntity.getEmail());
        return result;
    }
}
