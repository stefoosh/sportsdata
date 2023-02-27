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
public final class ReqResLogFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ReqResLogFilter.class);

    private ReqResLogFilter() {
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
//
//        LOG.debug("remoteAddr={} remoteHost={}", request.getRemoteAddr(), request.getRemoteHost());
//        String message = String.format("%s %s %s",
//                request.getMethod(),
//                response.getStatus(),
//                request.getRequestURL());
//        if (request.getQueryString() != null) {
//            message = message + "?" + request.getQueryString();
//        }
//
//        if (response.getStatus() > 500) {
//            LOG.error(message);
//        } else if (response.getStatus() > 200) {
//            LOG.warn(message);
//        } else {
//            logger.debug(message);
//        }
        filterChain.doFilter(request, response);
    }
}
