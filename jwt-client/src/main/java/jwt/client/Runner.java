package jwt.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@Slf4j
@Component
public class Runner {

    private final RestTemplate restTemplate = new RestTemplate();

    private static <T> HttpEntity<T> body(String token, T body) {
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(body, headers);
    }

    private static <T> HttpEntity<T> body(String token) {
        return body(token, null);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        var credentials = new Credentials(LocalDateTime.now().format(ofLocalizedDateTime(FormatStyle.SHORT)), "secret");
        restTemplate.postForEntity("http://localhost:9010/users", credentials, Void.class);
        var token = restTemplate.postForObject("http://localhost:9010/tokens", credentials, Token.class);
        log.info("Token obtained: {}", token.accessToken());

        restTemplate.postForEntity(
                "http://localhost:9020/movies",
                body(token.accessToken(), List.of("Taxi IV", "Pirates of the Caribbean")),
                Void.class
        );
        var movies = restTemplate.exchange(
                "http://localhost:9020/movies",
                HttpMethod.GET,
                body(token.accessToken()),
                Movie.LIST_TYPE
        ).getBody();
        log.info("Movies received: {}", movies);
    }

    record Movie(String ownerUsername, String name) {
        private static final ParameterizedTypeReference<List<Movie>> LIST_TYPE = new ParameterizedTypeReference<>() {
        };
    }

    record Token(String accessToken) {
    }

    record Credentials(String username, String password) {
    }
}
