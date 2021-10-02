package com.bithumb.interest.application;

import com.bithumb.coin.domain.Coin;
import com.bithumb.interest.api.dto.InterestResponse;
import com.bithumb.interest.domain.Interest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@Component
public interface InterestService {
    public InterestResponse createInterest(long userId, String symbol) throws IOException;
    public List<InterestResponse> getInterests(long userId);
    public void deleteInterest(String symbol, long userId);
}
