package leela;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "DELETE")

                .allowedOrigins("http://mocker.egen.io/?","http://localhost8080")

                .allowedHeaders("accepts", "Origin","Access-Control-Request-Method", "Access-Control-Request-Method")

                .allowCredentials(false).maxAge(3600)
    }
}
