package de.rjst.cs.logic.mapper;

import de.rjst.cs.api.model.CustomerDto;
import de.rjst.cs.database.CustomerEntity;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityMapper implements Function<CustomerDto, CustomerEntity> {

    @Override
    public CustomerEntity apply(final CustomerDto customerDto) {
        final var result = new CustomerEntity();
        result.setId(customerDto.getId());
        result.setFirstName(customerDto.getFirstName());
        result.setLastName(customerDto.getLastName());
        result.setBirthDate(customerDto.getBirthDate());
        result.setEmail(customerDto.getEmail());
        return result;
    }
}
