package hu.siraknorbert.homeproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class {@link SwaggerConfig} is used to define
 * beans in connection with swagger documentation generation.
 *
 * @author Norbert Sirák
 */
@Configuration
public class SwaggerConfig {

    /**
     * Create an {@link OpenAPI} bean that contains info settings
     * that are specific to the application.
     *
     * @return the created {@link OpenAPI} bean
     */
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Home Project API")
                        .description("Home Project application API documentation."));
    }
}
