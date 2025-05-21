package jwt.movies.service.movie;

import jwt.movies.service.database.Identifiable;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Movie implements Identifiable<UUID> {
    private final UUID id;
    private final String ownerUsername;
    private final String name;
}
