package com.example.demo.repository;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Created by Yurii on 2/22/2018.
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
}
