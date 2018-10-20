package filters;

import model.Record;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "Authentication")
public class AuthenticationFilter implements Filter {

    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) {
        this.excludedUrls = Arrays.asList(filterConfig.getInitParameter("excludedUrls").split(", *"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse)servletResponse;

        // check if url need user to be authenticated
        if (!this.excludedUrls.contains(httpReq.getRequestURI())){
            Record record = (Record) httpReq.getSession().getAttribute("user");
            if (record == null) {
                // user not authenticated; redirect to sign in page
                httpRes.sendRedirect("/signin");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
