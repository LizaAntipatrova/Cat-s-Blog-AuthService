package ru.meow.meowauth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import ru.meow.meowauth.httpresponse.HttpResponse;
import ru.meow.meowauth.services.AuthService;
import ru.meow.meowauth.services.RedisSessionService;
import ru.meow.meowauth.services.parser.CookieHeaderParser;

import java.io.IOException;


@RequiredArgsConstructor
public class SessionExistFilter implements Filter {

    private final RedisSessionService redisSessionService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest httpRequest) || !(response instanceof HttpServletResponse httpResponse)) {
            chain.doFilter(request, response);
            return;
        }


        String sessionId = CookieHeaderParser.getSessionIdCookie(httpRequest.getHeader("Cookie"), AuthService.COOKIE_HEADER_SESSION_ID_NAME);
        if (sessionId == null || sessionId.isEmpty()) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }

        RBucket<String> sessionBucket = redisSessionService.getRBucket(sessionId);
        if (!sessionBucket.isExists()) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }
}
