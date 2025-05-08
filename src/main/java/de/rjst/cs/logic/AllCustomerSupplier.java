package de.rjst.cs.logic;

import de.rjst.cs.api.openapi.model.CustomerDto;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AllCustomerSupplier implements Supplier<List<CustomerDto>> {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    public List<CustomerDto> get() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoMapper)
                .toList();
    }
}
