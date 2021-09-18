package com.bithumb.interest.repository;


import com.bithumb.interest.domain.Interest;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Repository
public interface InterestRepository extends ReactiveMongoRepository<Interest, Long> {
}
