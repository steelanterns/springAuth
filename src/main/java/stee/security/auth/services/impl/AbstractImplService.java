package stee.security.auth.services.impl;

import java.util.*;

public class AbstractImplService<T, ID> {
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
