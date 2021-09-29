package com.bithumb.coin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class CoinDto {
    private Coin[] data;
}