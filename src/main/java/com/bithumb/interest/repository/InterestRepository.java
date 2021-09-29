package com.bithumb.interest.repository;


import com.bithumb.interest.domain.Interest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface InterestRepository extends ReactiveMongoRepository<Interest, Long> {
    Flux<Interest> findByUserId(long userId);
    Mono<Boolean> existsInterestBySymbolAndUserId(String symbol,long userId);
    Mono<Void> deleteInterestBySymbolAndUserId(String symbol, long userId);
}
