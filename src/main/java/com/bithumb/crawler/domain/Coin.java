package com.bithumb.crawler.domain;

import lombok.*;

@Data @ToString
@RequiredArgsConstructor
public class Coin {
    private String symbol;
    private String market;
    private String korean;
}
