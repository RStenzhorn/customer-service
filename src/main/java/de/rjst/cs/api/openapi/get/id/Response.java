package de.rjst.cs.api.openapi.get.id;

import lombok.experimental.UtilityClass;

@UtilityClass
class Response {

    public final String SUCCESSFUL =
        """
            {
              "id": 1,
              "firstName": "string",
              "lastName": "string",
              "birthDate": "2025-04-05",
              "email": "string"
            }
            """;

    public final String CUSTOMER_NOT_FOUND =
        """
            {
              "timestamp": "2025-05-12T19:40:47.682+00:00",
              "status": 404,
              "error": "Not Found",
              "message": "Customer not found",
              "path": "/customers/9999999999"
            }
            """;
}
