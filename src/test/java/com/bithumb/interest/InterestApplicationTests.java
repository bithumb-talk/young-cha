package com.bithumb.interest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class InterestApplicationTests {

	@Test
	void contextLoads() {
	}

}
