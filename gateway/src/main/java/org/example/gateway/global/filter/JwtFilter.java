package org.example.gateway.global.filter;

import org.example.gateway.global.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) { super(Config.class); this.jwtUtil = jwtUtil; }

    public static class Config { }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain)-> {
            ServerHttpRequest request = exchange.getRequest();
            System.out.println(request.getURI().getPath());
            System.out.println(request.getURI().getPath());
            System.out.println(request.getURI().getPath());
            System.out.println(request.getURI().getPath());
            System.out.println(request.getURI().getPath());
            System.out.println(request.getURI().getPath());

            HttpCookie accessTokenCookie = null;
            HttpCookie refreshTokenCookie = null;
            HttpCookie typeCookie = null;
            MultiValueMap<String, HttpCookie> cookieArray = request.getCookies();
            // 쿠키가 없을 시 필터 통과
            if (cookieArray == null || cookieArray.isEmpty()) {
                String path = request.getURI().getPath();
                System.out.println(path);
                return chain.filter(exchange);
            }

            // 쿠키 매핑
            for (String cookieName : cookieArray.keySet()) {
                List<HttpCookie> cookies = cookieArray.get(cookieName);
                for (HttpCookie cookie : cookies) {
                    if (cookie.getName().equals("AToken")) {
                        accessTokenCookie = cookie;
                    }
                    if (cookie.getName().equals("RToken")) {
                        refreshTokenCookie = cookie;
                    }
                    if (cookie.getName().equals("type")) {
                        typeCookie = cookie;
                    }
                }
            }

            //로드밸런싱 함수 호출


            //넘겨받은 값이 없다 = 로그인안한거 -> 다 혀용된 URI만 접근가능
            //  근데 권한이 필요한 URI요청이다? -> 권한없음 응답. 아니면 통과

            //넘겨받은 값이 있다.
            // 토큰에서 값 추출 (idx, email, role)
            // 로드밸런싱할 때 권한비교
            //  -> /user/**이고 role == ROLE_USER -> 통과 아니면 권한없음
            //  -> /company/**이고 role == ROLE_COMPANY -> 통과 아니면 권한없음
            //  -> /orders/**이고 role == ROLE_USER or ROLE_COMPANY -> 통과 아니면 권한없음



            exchange.getRequest().mutate()
                    .header("X-User-Idx", ""+jwtUtil.getIdx(accessTokenCookie.getValue()))
                    .header("X-User-Email", jwtUtil.getEmail(accessTokenCookie.getValue()))
                    .header("X-User-Role", jwtUtil.getRole(accessTokenCookie.getValue()))
                    .build();

            return chain.filter(exchange);
        });
    }


}

