package jwt.authserver.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jwt.authserver.service.database.Database;
import jwt.authserver.service.user.Credentials;
import jwt.authserver.service.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;
    private final Database database;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Token issue(Credentials credentials) {
        var user = database.get(User.class, credentials.getUsername());
        if (!passwordEncoder.matches(credentials.getPassword(), user.getHashedPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (user.isDisabled()) {
            throw new IllegalStateException("User is disabled");
        }
        return Token.builder()
                .accessToken(generateAccessToken(user))
                .build();
    }

    private String generateAccessToken(User user) {
        var now = ZonedDateTime.now();
        return Jwts.builder()
                .issuer("thistle")
                .issuedAt(Date.from(now.toInstant()))
                .expiration(Date.from(now.plusMinutes(1).toInstant()))
                .subject(user.getUsername())
                .claim("scope", "profile movies books")
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getKey().getBytes()))
                .compact();
    }
}
