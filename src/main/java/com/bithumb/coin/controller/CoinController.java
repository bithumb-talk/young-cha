package com.bithumb.coin.controller;

import com.bithumb.coin.service.CoinService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Api
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false")
public class CoinController {
    private final CoinService coinService;
    @GetMapping("/coins")
    public void getInterests() throws IOException {

        coinService.getCoins();
    }

}
