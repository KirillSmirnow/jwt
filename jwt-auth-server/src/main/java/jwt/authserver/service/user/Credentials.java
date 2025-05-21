package jwt.authserver.service.user;

import lombok.Data;

@Data
public class Credentials {
    private final String username;
    private final String password;
}
