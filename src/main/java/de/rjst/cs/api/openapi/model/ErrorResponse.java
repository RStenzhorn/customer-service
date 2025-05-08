package de.rjst.cs.api.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {

    @Schema(description = "Error message", requiredMode = RequiredMode.REQUIRED)
    private String message;

    @Schema(description = "Validation errors", requiredMode = RequiredMode.REQUIRED)
    private Map<String, String> errors;

}
