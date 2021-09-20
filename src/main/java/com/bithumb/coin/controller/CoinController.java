package com.bithumb.coin.controller;

import com.bithumb.coin.controller.dto.InterestDto;
import com.bithumb.coin.domain.Coin;
import com.bithumb.coin.repository.CoinRepository;
import com.bithumb.common.response.ApiResponse;
import com.bithumb.common.response.ErrorCode;
import com.bithumb.common.response.StatusCode;
import com.bithumb.common.response.SuccessCode;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowCredentials = "false")
public class CoinController {
    private final CoinRepository coinRepository;

    @GetMapping("/coins")
    public ResponseEntity<?> getInterests(){
        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
                SuccessCode.INTEREST_FINDALL_SUCCESS.getMessage());
        apiResponse.setData(coinRepository.findAll().collectList().block());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
