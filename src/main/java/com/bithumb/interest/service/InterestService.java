package com.bithumb.interest.service;

import com.bithumb.interest.domain.Interest;
import org.springframework.stereotype.Component;

@Component
public interface InterestService {
    public Interest createInterest();
}
