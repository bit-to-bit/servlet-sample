package org.java.dev.servlet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java.dev.util.ParameterHandler;

import java.io.IOException;

@WebFilter
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");
        if (req.getParameterMap().size() > 0 && !ParameterHandler.isValidTimezone(timezone)) {
            sendInvalidParameter(resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    private static void sendInvalidParameter(HttpServletResponse resp) throws IOException {
        final int badRequest = 400;
        resp.setStatus(badRequest);
        resp.getWriter().write("Invalid timezone");
        resp.getWriter().close();
    }
}