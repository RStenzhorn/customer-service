package de.rjst.cs.logic;

import de.rjst.cs.database.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class DeleteCustomerFunction implements Consumer<Long> {

    private final CustomerRepository customerRepository;

    @Override
    public void accept(final Long id) {
        customerRepository.deleteById(id);
    }
}
