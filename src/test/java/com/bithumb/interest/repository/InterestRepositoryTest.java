package com.bithumb.interest.repository;

import com.bithumb.interest.domain.Interest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootApplication
class InterestRepositoryTest{
    @Autowired
    private InterestRepository interestRepository;

    @Test
    void save() {
        //given
        final Interest interest = Interest.builder()
                .userId(1)
                .market("BTC_KRW")
                .korean("비트코인")
                .symbol("BTC")
                .build();
        //when
        final Interest savedInterest = interestRepository.save(interest);
        //then
        assertThat(savedInterest.getKorean(),is(equalTo("비트코인")));
    }

    @Test
    void findByUserId() {
    }

    @Test
    void existsInterestBySymbolAndUserId() {
    }

    @Test
    void deleteInterestBySymbolAndUserId() {
    }

}