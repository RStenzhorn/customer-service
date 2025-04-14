package de.rjst.cs.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.CustomerControllerApi;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    private static final String URL = "http://localhost:%d";

    @Bean
    public ApiClient apiClient(final ConfigurableWebServerApplicationContext webServerAppCtxt) {
        final var webServer = webServerAppCtxt.getWebServer();
        final var port = webServer.getPort();
        final var baseUrl = URL.formatted(port);
        final var config = ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setBaseUri(baseUrl));
        return ApiClient.api(config);
    }

    @Bean
    public CustomerControllerApi customerControllerApi(final ApiClient apiClient) {
        return apiClient.customerController();
    }

}
