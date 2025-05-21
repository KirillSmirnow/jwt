package jwt.authserver.service.database;

import java.util.List;
import java.util.function.Predicate;

public interface Database {

    void put(Identifiable<?> object);

    <T extends Identifiable<ID>, ID> T get(Class<T> type, ID id);

    <T extends Identifiable<?>> List<T> getAll(Class<T> type, Predicate<T> filter);
}
