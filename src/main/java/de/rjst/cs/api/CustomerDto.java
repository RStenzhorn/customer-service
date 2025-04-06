package de.rjst.cs.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;

}
