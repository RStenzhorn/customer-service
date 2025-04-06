package de.rjst.cs.logic;

import de.rjst.cs.database.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class DeleteCustomerFunction implements Consumer<Long> {

    private final CustomerRepository customerRepository;

    @Override
    public void accept(final Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.deleteById(id);

    }
}
