package jwt.authserver.controller;

import jwt.authserver.service.user.Credentials;
import jwt.authserver.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody Credentials credentials) {
        userService.create(credentials);
    }
}
