package com.bithumb.interest.api.dto;

import com.bithumb.interest.domain.Interest;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder @ToString
public class InterestResponse {
    private long userId;
    private String symbol;
    private String korean;
    private String market;

    public static InterestResponse of(Interest interest, long userId) {
        return new InterestResponse(userId, interest.getSymbol(),interest.getKorean(),interest.getMarket());
    }

    public static List<InterestResponse> ofArray(List<Interest> interests, long userId) {
        List<InterestResponse> interestsResponse = new ArrayList<>();
        for (Interest interest: interests) {
            interestsResponse.add(InterestResponse.of(interest,userId));
        }
        return interestsResponse;
    }
}
