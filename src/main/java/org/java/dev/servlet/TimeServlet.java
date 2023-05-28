package org.java.dev.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java.dev.util.ParameterHandler;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet
public class TimeServlet extends HttpServlet {
    private static final String PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";
        resp.setContentType("text/html; charset=utf-8");
        if (req.getParameterMap().size() == 0) {
            message = getRequestTime("UTC");
        } else {
            message = getRequestTime(req.getParameter("timezone"));
        }
        resp.getWriter().write(message);
        resp.getWriter().close();
    }

    private static String getRequestTime(String timezone) {
        String result = "";
        String formattedTimezone = ParameterHandler.formatInputTimezone(timezone);
        Instant instant = Instant.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(PATTERN_FORMAT)
                    .withZone(ZoneId.of(formattedTimezone));
            result = formatter.format(instant) + " " + formattedTimezone;
        } catch (Exception е) {
            result = "You specified an incorrect time zone in the request: " + formattedTimezone;
        }
        return result;
    }
}