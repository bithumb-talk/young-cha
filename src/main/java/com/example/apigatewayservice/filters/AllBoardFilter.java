package com.example.apigatewayservice.filters;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AllBoardFilter extends AbstractGatewayFilterFactory<Config> {
	public AllBoardFilter() {
		super(Config.class);
	}

	@Autowired
	private Environment env;

	@Override
	public GatewayFilter apply(final Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onErrorResponse(exchange, 901, "No Authorization header");
			}

			// Request Header 에 token 이 존재하지 않을 때
			if (!request.getHeaders().containsKey("Authorization")) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				return response.setComplete();
			}

			String pathString = request.getURI().getPath();
			String[] extractPath = pathString.split("/");

			boolean checkAuthResult = false;
			for (String checkAuth : extractPath){
				if (checkAuth.equals("auth")){
					checkAuthResult = true;
				}
			}

			// 토큰이 필요 없는 url이면 통과
			if( checkAuthResult == false){
				return chain.filter(exchange);
			}



			// Request Header 에서 token 문자열 받아오기
			List<String> token = request.getHeaders().get("Authorization");
			String tokenString = Objects.requireNonNull(token).get(0);
			String jwt = resolveToken(tokenString);

			int responseValue = 0;

			try {
				responseValue = isJwtValid(jwt);
			} catch (Exception e) {
				responseValue = 904;
			}

			System.out.println("responseValue : "+ responseValue);

			if (responseValue == 902) {
				return onErrorResponse(exchange, responseValue, "ID NOT MATCH IN GATEWAY");
			} else if (responseValue == 903) {
				return onErrorResponse(exchange, responseValue, "EXPIRED TOKEN IN GATEWAY");
			} else if (responseValue == 904) {
				return onErrorResponse(exchange, responseValue, "NOT VALID TOKEN IN GATEWAY");
			} else if (responseValue == 905) {
				return onErrorResponse(exchange, responseValue, "NOT VALID TOKEN IN GATEWAY");
			}


			return chain.filter(exchange); // 토큰이 일치할 때
		};
	}


	public Mono<Void> onErrorResponse(ServerWebExchange exchange, int errorCode, String errorMsg) {
		String errorcode = "{\"status\":" + "FAIL" + ","
			+ "\"message\":" + "\"" + errorMsg + "\"" + "}";
		byte[] bytes = errorcode.getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
		return exchange.getResponse().writeWith(Flux.just(buffer));
	}

	private int isJwtValid(String jwt) {
		String subject = null;

		try {
			subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
				.parseClaimsJws(jwt).getBody().getSubject();

		} catch (ExpiredJwtException e) {
			return 903;
		} catch (Exception ex) {
			return 904;
		}

		if (subject == null || subject.isEmpty()) {
			return 905;
		}

		System.out.println("return 2000");
		return 200;
	}

	private String resolveToken(String rawToken) {
		String bearerToken = rawToken;
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
