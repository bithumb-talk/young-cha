package com.bithumb.coin.repository;

import com.bithumb.coin.domain.Coin;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CoinRepository extends ReactiveMongoRepository<Coin,Long> {
    Mono<Coin> findBySymbol(String symbol);
    Mono<Boolean> existsCoinBySymbol(String symbol);
    @Query("{'symbol': {$regex: ?0}}")
    Mono<Coin> findRegexBySymbol(String symbol);
}
