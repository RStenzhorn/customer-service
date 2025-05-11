package de.rjst.cs.api.openapi.get.all;

import lombok.experimental.UtilityClass;

@UtilityClass
class Response {

    public final String SUCCESSFUL =
        """
            [
                  {
                        "id": 1,
                        "firstName": "string",
                        "lastName": "string",
                        "birthDate": "2025-04-05",
                        "email": "string"
                  }
            ]
            """;

}
