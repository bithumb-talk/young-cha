package com.bithumb.interest.application;

import com.bithumb.coin.service.CoinServiceImpl;
import com.bithumb.interest.api.dto.InterestResponse;
import com.bithumb.interest.domain.Interest;
import com.bithumb.interest.repository.InterestRepository;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InterestServiceImplTest {

    @InjectMocks
    InterestServiceImpl interestService;

    @Mock
    private InterestRepository interestRepository;
    @Mock
    private CoinServiceImpl coinService;

    final Interest interest = Interest.builder()
            .userId(1)
            .build();
    final List<Interest> interests = Arrays.asList(interest);

    final InterestResponse interestResponse = InterestResponse.of(interest, 1);
    @BeforeEach
    void setUp() {
        this.interestService = new InterestServiceImpl(interestRepository, coinService);
    }

    @Test
    @DisplayName("관심코인 조회")
    void getInterests() {
        given(interestRepository.findByUserId(any())).willReturn(interests);

        System.out.println(interestService.getInterests(1));

//        assertThat(result.size(),is(1));
    }

    @Test
    @AutoConfigureTestDatabase
    void createInterest() throws IOException {

        InterestResponse interestResponse = interestService.createInterest(interest.getUserId(),interest.getSymbol());

        assertThat(interestResponse.getKorean(),is(equalTo("비트코인")));

    }

    @Test
    void deleteInterest() {

    }
}