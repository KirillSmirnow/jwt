package jwt.authserver.controller;

import jwt.authserver.service.token.Token;
import jwt.authserver.service.token.TokenService;
import jwt.authserver.service.user.Credentials;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/tokens")
    public Token issueToken(@RequestBody Credentials credentials) {
        return tokenService.issue(credentials);
    }
}
