package jwt.movies.service.movie;

import java.util.List;

public interface MovieService {

    void add(String name);

    List<Movie> getMine();
}
