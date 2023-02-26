package sh.stefoosh.sportsdata.application.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class ReqResLogFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ReqResLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String message = String.format("%s %s %s",
                request.getMethod(),
                response.getStatus(),
                request.getRequestURI());

        if (request.getQueryString() != null) {
            message = message + "?" + request.getQueryString();
        }
        LOG.debug(message);
        LOG.debug("remoteAddr={} remoteHost={}", request.getRemoteAddr(), request.getRemoteHost());

        filterChain.doFilter(request, response);
    }
}
