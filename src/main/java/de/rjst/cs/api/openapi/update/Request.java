package de.rjst.cs.api.openapi.update;

import lombok.experimental.UtilityClass;

@UtilityClass
class Request {

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

    public final String INVALID_FIRST_NAME =
        """
            {
              "id": 1,
              "firstName": "",
              "lastName": "Mustermann",
              "birthDate": "2025-04-05",
              "email": null
            }
            """;

    public final String INVALID_LAST_NAME =
        """
            {
              "id": 1,
              "firstName": "string",
              "lastName": "",
              "birthDate": "2025-04-05",
              "email": null
            }
            """;

    public final String INVALID_BIRTH_DATE =
        """
            {
              "id": 1,
              "firstName": "string",
              "lastName": "string",
              "birthDate": "",
              "email": null
            }
            """;

    public final String CUSTOMER_NOT_FOUND =
        """
            {
              "id": 999999999,
              "firstName": "string",
              "lastName": "string",
              "birthDate": "2025-04-05",
              "email": "string"
            }
            """;
}
