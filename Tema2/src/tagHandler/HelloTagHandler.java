package tagHandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Camelia on 10/20/2018.
 */
public class HelloTagHandler  extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        // Create dynamic content
        JspWriter out = getJspContext().getOut();
        out.print("Hello World!");
    }

}
