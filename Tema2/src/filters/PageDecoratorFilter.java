package filters;

import utils.CharResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class PageDecoratorFilter implements Filter {

    private static final String HEADER = "<div id=\"_head\">%s</div>";
    private static final String FOOTER = "<div id=\"_footer\">%s</div>";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        System.out.println("DECORATOR FILTER");
        CharResponseWrapper wrapper =
                new CharResponseWrapper( (HttpServletResponse)response );

        filterChain.doFilter(request, wrapper);
        String content = wrapper.toString();
        content = content.replace(HEADER, String.format(HEADER, "<h2>Header</h2>"));
        content = content.replace(FOOTER, String.format(FOOTER, "<footer>&copy;2018</footer>"));
        PrintWriter out = response.getWriter();
        out.write(content);
    }

    @Override
    public void destroy() {

    }
}
