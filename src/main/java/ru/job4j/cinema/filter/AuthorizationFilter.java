package ru.job4j.cinema.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse responce, FilterChain chain) throws IOException, ServletException {
        var uri = request.getRequestURI();
        if (uri.startsWith("/schedule/buy")) {
            var userLoggedIn = request.getSession().getAttribute("user") != null;
            if (!userLoggedIn) {
                var loginPageUrl = request.getContextPath() + "/users/login";
                responce.sendRedirect(loginPageUrl);
            }
        }
        /*if (isAlwaysPermitted(uri)) {
            chain.doFilter(request, responce);
            return;
        }
        var userLoggedIn = request.getSession().getAttribute("user") != null;
        if (!userLoggedIn) {
            var loginPageUrl = request.getContextPath() + "users/login";
            responce.sendRedirect(loginPageUrl);
        }*/
        chain.doFilter(request, responce);
    }

    /*private boolean isAlwaysPermitted(String uri) {
        return uri.startsWith("/users/register")
                || uri.startsWith("/users/login")
                || uri.startsWith("/films")
                || uri.startsWith("/schedule")
                || uri.startsWith("/errors/404")
                || uri.startsWith("/")
                || uri.startsWith("/js")
                || uri.startsWith("/css");
    }*/
}
