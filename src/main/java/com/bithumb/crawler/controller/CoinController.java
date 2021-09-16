package com.bithumb.crawler.controller;

import com.bithumb.crawler.common.response.ApiResponse;
import com.bithumb.crawler.domain.Coin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RestController
public class CoinController {
    @GetMapping("/coins")
    public ResponseEntity<Flux<List<Coin>>> getCoins() throws IOException {
        List<Coin> coins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader("bithumb.csv")
        );
        String str;
        int i = 0;
        while((str = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str,",");
            Coin coin = new Coin();
            coin.setSymbol(st.nextToken());
            coin.setMarket(st.nextToken());
            coin.setKorean(st.nextToken());
            coins.add(coin);
        }
        System.out.println(coins);
        reader.close();
        return ResponseEntity.ok(Flux.just(coins));
    }
}
