package com.zmh.springbootdemo.repository;

import com.zmh.springbootdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link com.zmh.springbootdemo.domain.User}{@link org.springframework.stereotype.Repository}
 */

@Repository
public class UserRespository {

    private final ConcurrentHashMap<Integer,User> respository = new ConcurrentHashMap<>();
    private final  static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user){
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return respository.put(id,user) == null;
    }

    public Collection<User> findAll(){
        return respository.values();
    }

}
