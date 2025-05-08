package de.rjst.cs.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CustomerDto {

    @Schema(description = "The ID of the customer", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private Long id;

    @Schema(description = "The first name of the customer", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private String firstName;

    @Schema(description = "The last name of the customer", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private String lastName;

    @Schema(description = "The birth date of the customer", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private LocalDate birthDate;

    @Schema(description = "The email of the customer")
    private String email;
}
