package de.rjst.cs.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
public class DefaultErrorResponse {

    @Schema(description = "Timestamp of the error", requiredMode = RequiredMode.REQUIRED)
    private ZonedDateTime timestamp;

    @Schema(description = "HttpStatusCode", requiredMode = RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "HttpText", requiredMode = RequiredMode.REQUIRED)
    private String error;

    @Schema(description = "Reason for ErrorCode", requiredMode = RequiredMode.REQUIRED)
    private String message;

    @Schema(description = "Path", requiredMode = RequiredMode.REQUIRED)
    private String path;

}
