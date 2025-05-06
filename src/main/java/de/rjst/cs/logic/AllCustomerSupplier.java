package de.rjst.cs.logic;

import de.rjst.cs.api.CustomerDto;
import de.rjst.cs.database.CustomerRepository;
import de.rjst.cs.logic.mapper.CustomerDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

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
