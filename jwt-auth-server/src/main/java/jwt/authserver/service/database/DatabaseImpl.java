package jwt.authserver.service.database;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Component
public class DatabaseImpl implements Database {

    private final Map<Class<?>, Map<Object, Object>> objects = new ConcurrentHashMap<>();

    @Override
    public void put(Identifiable<?> object) {
        objects.computeIfAbsent(object.getClass(), $ -> new ConcurrentHashMap<>())
                .put(object.getId(), object);
    }

    @Override
    public <T extends Identifiable<ID>, ID> T get(Class<T> type, ID id) {
        return (T) objects.get(type).get(id);
    }

    @Override
    public <T extends Identifiable<?>> List<T> getAll(Class<T> type, Predicate<T> filter) {
        return objects.get(type).values().stream()
                .map(item -> (T) item)
                .filter(filter)
                .toList();
    }
}
