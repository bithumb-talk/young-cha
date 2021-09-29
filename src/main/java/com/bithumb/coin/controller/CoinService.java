package com.bithumb.coin.controller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.StringUtils;
import com.bithumb.coin.domain.Coin;
import com.bithumb.coin.domain.CoinDto;
import com.bithumb.common.response.ApiResponse;
import com.bithumb.common.response.ErrorCode;
import com.bithumb.common.response.StatusCode;
import com.bithumb.common.response.SuccessCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;


@RequiredArgsConstructor
@Service
public class CoinService {
    public HashMap<String, Coin> getCoins() throws IOException {
        String bucket_name = "youngcha-coin-service";
        String key_name = "coinlist/coin-list.json";
        ObjectMapper objectMapper = new ObjectMapper();

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA2SJCWGIOHZXVYN5I", "KFpI6M/M6TSYVTQt13MFnv9LrdU9QDzR3Kzm2Oc2")))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
        S3Object o = s3.getObject(bucket_name,key_name);
        S3ObjectInputStream s3is = o.getObjectContent();
        Coin[] coins = objectMapper.readValue(s3is,Coin[].class);
        HashMap<String, Coin> map = new HashMap<String, Coin>();
        int i;
        for (i=0;i<coins.length;i++) {
            map.put(coins[i].getSymbol(),coins[i]);
        }
        return map;
    }
}