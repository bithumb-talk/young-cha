package com.bithumb.coin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode @ToString
@Document(collection = "coins")
public class Coin {
    @Id
    private ObjectId id;
    private String symbol;
    private String korean;
    private String market;
}
