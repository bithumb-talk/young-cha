package com.bithumb.interest.repository;

import com.bithumb.interest.domain.Interest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootApplication
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackages = {"com.bithumb.interest.domain"})
class InterestRepositoryTest{
    @Autowired
    private InterestRepository interestRepository;


    @Test
    void save() {
        //given
        Interest interest = Interest.builder()
                .userId(1)
                .market("BTC_KRW")
                .korean("비트코인")
                .symbol("BTC")
                .build();
        //when
        System.out.println(interest);
        interestRepository.save(interest);
        //then
//        assertThat(savedInterest.getKorean(),is(equalTo("비트코인")));
    }

    @Test
    void findByUserId() {
        Interest interest = Interest.builder()
                .userId(1)
                .market("BTC_KRW")
                .korean("비트코인")
                .symbol("BTC")
                .build();
        //when
        System.out.println(interest);
        interestRepository.save(interest);
    }

    @Test
    void existsInterestBySymbolAndUserId() {
    }

    @Test
    void deleteInterestBySymbolAndUserId() {
    }

}