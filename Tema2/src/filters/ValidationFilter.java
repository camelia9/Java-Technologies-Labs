package filters;

import utils.Routes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidationFilter implements Filter {

    private static final String EMAIL_REGEX = "\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"";
    private static final String DEFAULT_EMAIL_VALUE = "not specified";
    private Pattern emailValidator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        emailValidator = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase("POST")){
            String email = req.getParameter("email");
            if(email != null && !email.isEmpty()){
                if (!emailValidator.matcher(email).matches()) {
                    res.sendRedirect(Routes.SIGN_UP_ROUTE);
                    return;
                }
            }
            else{
                HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req);
                requestWrapper.setAttribute("email", DEFAULT_EMAIL_VALUE);
                servletRequest = requestWrapper;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
