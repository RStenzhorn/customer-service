package de.rjst.cs.api.openapi.create;

import lombok.experimental.UtilityClass;

@UtilityClass
class Response {

    public final String SUCCESSFUL = """
        {
          "id": 1,
          "firstName": "string",
          "lastName": "string",
          "birthDate": "2025-04-05",
          "email": "string"
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
}
