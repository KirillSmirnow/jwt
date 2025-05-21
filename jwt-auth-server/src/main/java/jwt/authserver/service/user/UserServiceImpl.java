package jwt.authserver.service.user;

import jwt.authserver.service.database.Database;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Database database;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(Credentials credentials) {
        var user = User.builder()
                .username(credentials.getUsername())
                .hashedPassword(passwordEncoder.encode(credentials.getPassword()))
                .build();
        database.put(user);
    }
}
