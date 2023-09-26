package stee.security.auth.services.map;

import java.util.*;

/**
 * Created by Steeve Jean Chilles on 09/22/2023
 */
public abstract class AbstractMapService<T, ID> {
    protected Map<ID, T> map = new HashMap<>();

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    Optional<T> findById(ID id){
        T result = map.get(id);
        return Optional.ofNullable(result);
    }

    T save( ID id, T object ){
        map.put(id, object);
        return object;
    }

    void deleteById( ID id ){
        map.remove(id);
    }

    void delete( T object ){
        map.entrySet().removeIf( entry -> entry.getValue().equals(object) );
    }
}
