package jwt.authserver.service.user;

import jwt.authserver.service.database.Identifiable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements Identifiable<String> {

    private final String username;
    private final String hashedPassword;
    private boolean disabled;

    @Override
    public String getId() {
        return username;
    }
}
