package de.rjst.cs.api.openapi.update;

import lombok.experimental.UtilityClass;

@UtilityClass
class Response {

    public final String SUCCESSFUL =
        """
            {
              "id": {{ request.body/id }},
              "firstName": "{{ request.body/firstName }}",
              "lastName": "{{ request.body/lastName }}",
              "birthDate": "{{ request.body/birthDate }}",
              "email": "{{ request.body/email }}"
            }
            """;

    public final String INVALID_FIRST_NAME = """
        {
          "message": "Validierungsfehler",
          "errors": {
            "firstName": "firstName darf nicht leer sein"
          }
        }
        """;

    public final String INVALID_LAST_NAME = """
        {
          "message": "Validierungsfehler",
          "errors": {
            "lastName": "lastName darf nicht leer sein"
          }
        }
        """;
    public final String INVALID_BIRTH_DATE =
        """
            {
              "message": "Validierungsfehler",
              "errors": {
                "virthdate": "Birthdate darf nicht leer sein"
              }
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
