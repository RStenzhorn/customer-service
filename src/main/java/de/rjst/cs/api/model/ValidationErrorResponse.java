package de.rjst.cs.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ValidationErrorResponse {

    @Schema(description = "Error message", requiredMode = RequiredMode.REQUIRED)
    private String message;

    @Schema(description = "Validation errors", requiredMode = RequiredMode.REQUIRED)
    private Map<String, String> errors;

}
