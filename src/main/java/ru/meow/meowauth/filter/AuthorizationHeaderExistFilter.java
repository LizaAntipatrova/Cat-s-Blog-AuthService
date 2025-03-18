package ru.meow.meowauth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import ru.meow.meowauth.httpresponse.HttpResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationHeaderExistFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest httpRequest) || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty()) {
            response = HttpResponse.UNAUTHORIZED.getResponse((HttpServletResponse) response);
            return;
        }
        chain.doFilter(request, response);

    }
}
