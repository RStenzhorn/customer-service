package de.rjst.cs.api.openapi.create;

import lombok.experimental.UtilityClass;

@UtilityClass
class Request {

    public final String SUCCESSFUL =
        """
            {
              "firstName": "Max",
              "lastName": "Mustermann",
              "birthDate": "2025-04-05",
              "email": "max.mustermann@web.de"
            }
            """;

    public final String INVALID_FIRST_NAME =
        """
            {
              "firstName": "",
              "lastName": "Mustermann",
              "birthDate": "2025-04-05",
              "email": null
            }
            """;

    public final String INVALID_LAST_NAME =
        """
            {
              "firstName": "string",
              "lastName": "",
              "birthDate": "2025-04-05",
              "email": null
            }
            """;

    public final String INVALID_BIRTH_DATE =
        """
            {
              "firstName": "string",
              "lastName": "string",
              "birthDate": "",
              "email": null
            }
            """;
}
