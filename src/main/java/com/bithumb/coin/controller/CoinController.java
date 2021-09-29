package com.bithumb.coin.controller;

import com.bithumb.common.response.ApiResponse;
import com.bithumb.common.response.ErrorCode;
import com.bithumb.common.response.StatusCode;
import com.bithumb.common.response.SuccessCode;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Api
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false")
public class CoinController {
//    private final CoinRepository coinRepository;
    private final CoinService coinService;
    @GetMapping("/coins")
    public void getInterests() throws IOException {
//        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
//                SuccessCode.INTEREST_FINDALL_SUCCESS.getMessage());
//        apiResponse.setData(coinRepository.findAll().collectList().block());
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        coinService.getCoins();
    }

}
