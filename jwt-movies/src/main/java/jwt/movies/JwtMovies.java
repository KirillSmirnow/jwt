package jwt.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JwtMovies {

    public static void main(String[] args) {
        SpringApplication.run(JwtMovies.class, args);
    }
}
