package com.bithumb.interest.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode @ToString
@AllArgsConstructor @Builder
@Document(collection = "interests")
public class Interest {
    @Id
    private ObjectId id;
    private String symbol;
    private String korean;
    private String market;
    private long userId;

}
