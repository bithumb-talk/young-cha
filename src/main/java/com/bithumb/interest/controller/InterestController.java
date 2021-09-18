package com.bithumb.interest.controller;

import com.bithumb.interest.domain.Coin;
import com.bithumb.interest.domain.Interest;
import com.bithumb.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false")
public class InterestController {
    private final InterestRepository interestRepository;

    @GetMapping("/interests/{user-id}")
    public Mono<ResponseEntity<Interest>> getInterests(@PathVariable(value = "user-id") long id){
        return interestRepository.findById(id).map(i -> ResponseEntity.ok(i));
    }

//        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.FAIL,
//                ErrorCode.ID_NOT_EXIST.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);


    @PostMapping("/interest/{user-id}")
    public Mono<ResponseEntity<Interest>> createInterest(@PathVariable(value = "user-id") long id, @RequestBody Coin coin){
        boolean exsist =  interestRepository.existsById(id).block().booleanValue();
        if (!exsist) {
            List<Coin> defaultCoins = new ArrayList<>();
            defaultCoins.add(coin);
            Interest interest = new Interest(id,defaultCoins);
            return interestRepository.save(interest).map( j -> { return ResponseEntity.ok(j);})
                    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return interestRepository.findById(id).flatMap(i -> {
            if (i.getCoins().contains(coin)) {
                return null;
            }
            List<Coin> coins = new ArrayList<>();
            coins.add(coin);
            coins.addAll(i.getCoins());
            i.setCoins(coins);
            return interestRepository.save(i).map( j -> { return ResponseEntity.ok(j);})
                    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        });
    }

    @DeleteMapping("/interest/{user-id}")
    public Mono<ResponseEntity<Interest>> deleteInterest(@PathVariable(value = "user-id") long id, @RequestBody Coin coin) {
        List<Coin> coins = interestRepository.findById(id).block().getCoins();
        coins.remove(coin);

        return interestRepository.save(new Interest(id, coins)).map(j -> {return ResponseEntity.ok(j);} )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
