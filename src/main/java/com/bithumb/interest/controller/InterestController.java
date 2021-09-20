package com.bithumb.interest.controller;

import com.bithumb.coin.domain.Coin;
import com.bithumb.coin.repository.CoinRepository;
import com.bithumb.common.response.ApiResponse;
import com.bithumb.common.response.ErrorCode;
import com.bithumb.common.response.StatusCode;
import com.bithumb.common.response.SuccessCode;
import com.bithumb.interest.controller.dto.InterestDto;
import com.bithumb.interest.domain.Interest;
import com.bithumb.interest.repository.InterestRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequestMapping
@RequiredArgsConstructor
@RestController
@Api
@CrossOrigin(origins = "*", allowCredentials = "false")
public class InterestController {
    private final InterestRepository interestRepository;
    private final CoinRepository coinRepository;

    @GetMapping("/interests/{user-id}")
    public ResponseEntity<?> getInterests(@PathVariable(value = "user-id") long userId){
        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
                SuccessCode.INTEREST_FINDALL_SUCCESS.getMessage());
        apiResponse.setData(interestRepository.findByUserId(userId).collectList().block());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/interest/{user-id}")
    public ResponseEntity<?> createInterest(@PathVariable(value = "user-id") long userId, @RequestBody InterestDto symbol){
        Boolean exsitsCoin = coinRepository.existsCoinBySymbol(symbol.getSymbol()).block().booleanValue();
        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
                SuccessCode.INTEREST_CREATE_SUCCESS.getMessage());
        if (!exsitsCoin) {
            apiResponse.setMessage(ErrorCode.COIN_NOT_EXISTS.getMessage());
            apiResponse.setStatus(StatusCode.FAIL);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        Boolean existsDB = interestRepository.existsInterestBySymbolAndUserId(symbol.getSymbol(),userId).block().booleanValue();
        if (existsDB) {
            apiResponse.setStatus(StatusCode.ERROR);
            apiResponse.setMessage(ErrorCode.ALREADY_EXISTS);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiResponse);
        }
        Coin coin = coinRepository.findRegexBySymbol(symbol.getSymbol()).block();

        Interest interest = new Interest();
        interest.setUserId(userId);
        interest.setKorean(coin.getKorean());
        interest.setMarket(coin.getMarket());
        interest.setSymbol(symbol.getSymbol());
        apiResponse.setData(interestRepository.save(interest).block());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/interest/{user-id}")
    public ResponseEntity<?> deleteInterest(@PathVariable(value = "user-id") long userId, @RequestBody InterestDto symbol) {
        ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS,
                SuccessCode.INTEREST_DELETE_SUCCESS.getMessage());
        Boolean exists = interestRepository.existsInterestBySymbolAndUserId(symbol.getSymbol(), userId).block().booleanValue();
        if (!exists) {
            apiResponse.setStatus(StatusCode.FAIL);
            apiResponse.setMessage(ErrorCode.INTEREST_NOT_EXISTS);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiResponse);
        }
        apiResponse.setData(interestRepository.deleteInterestBySymbolAndUserId(symbol.getSymbol(),userId).block());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
