package com.bithumb.interest.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor @ToString
@Document(collection = "coins")
public class Coin {
    @Id
    private long id;
    private String symbol;
    private String market;
    private String korean;
}
