package jwt.movies.security.user;

public interface CurrentUser {

    boolean isAuthenticated();

    String getUsername();
}
