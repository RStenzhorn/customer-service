package de.rjst.cs.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorDto {

    private Map<String, String> errors;

}
