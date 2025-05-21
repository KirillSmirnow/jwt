package jwt.movies.controller;

import jwt.movies.service.movie.Movie;
import jwt.movies.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    @PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_movies')")
    public List<Movie> getMyMovies() {
        return movieService.getMine();
    }

    @PostMapping("/movies")
    @PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_movies')")
    public void addMovie(@RequestBody List<String> movies) {
        movies.forEach(movieService::add);
    }
}
