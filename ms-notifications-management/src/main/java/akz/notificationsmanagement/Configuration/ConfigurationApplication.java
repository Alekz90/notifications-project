package akz.notificationsmanagement.Configuration;

import akz.commonutils.config.SwaggerConfiguration;
import akz.commonutils.util.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApplication {

  @Bean
  public SwaggerProperties swaggerProperties() {
    return new SwaggerProperties();
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new SwaggerConfiguration(swaggerProperties()).customOpenAPI();
  }
}
