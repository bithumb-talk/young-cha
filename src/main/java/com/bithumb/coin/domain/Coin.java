package com.bithumb.coin.domain;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode @ToString @Builder
public class Coin {
    private String symbol;
    private String korean;
    private String market;
}
