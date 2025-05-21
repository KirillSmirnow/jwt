package jwt.movies.security.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserImpl implements CurrentUser {

    @Override
    public boolean isAuthenticated() {
        return getAuthentication().isPresent();
    }

    @Override
    public String getUsername() {
        return getAuthentication()
                .map(Authentication::getName)
                .orElseThrow(() -> new IllegalStateException("User is not authenticated"));
    }

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }
}
