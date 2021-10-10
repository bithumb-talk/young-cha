package com.bithumb.interest.application;

import com.bithumb.interest.api.dto.InterestResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface InterestService {
    public InterestResponse createInterest(long userId, String symbol) throws IOException;
    public List<InterestResponse> getInterests(long userId);
    public void deleteInterest(String symbol, long userId);
}
