package jwt.authserver.service.token;

import jwt.authserver.service.user.Credentials;

public interface TokenService {

    Token issue(Credentials credentials);
}
