package com.bithumb.interest.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Document(collection = "interests")
public class Interest {
    @Id
    private long id;
    private List<Coin> coins;
}
