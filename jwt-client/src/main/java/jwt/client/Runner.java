package jwt.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

@Slf4j
@Component
public class Runner {

    private final RestTemplate restTemplate = new RestTemplate();

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        var credentials = new Credentials(LocalDateTime.now().format(ofLocalizedDateTime(FormatStyle.SHORT)), "secret");
        restTemplate.postForEntity("http://localhost:9010/users", credentials, Void.class);
        var token = restTemplate.postForObject("http://localhost:9010/tokens", credentials, Token.class);
        log.info("Token obtained: {}", token.accessToken());
    }

    record Token(String accessToken) {
    }

    record Credentials(String username, String password) {
    }
}
