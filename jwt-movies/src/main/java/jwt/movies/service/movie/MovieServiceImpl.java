package jwt.movies.service.movie;

import jwt.movies.security.user.CurrentUser;
import jwt.movies.service.database.Database;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final CurrentUser currentUser;
    private final Database database;

    @Override
    public void add(String name) {
        var movie = Movie.builder()
                .id(randomUUID())
                .ownerUsername(currentUser.getUsername())
                .name(name)
                .build();
        database.put(movie);
    }

    @Override
    public List<Movie> getMine() {
        return database.getAll(Movie.class, movie -> movie.getOwnerUsername().equals(currentUser.getUsername()));
    }
}
