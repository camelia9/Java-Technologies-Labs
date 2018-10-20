package tagHandler;

import model.Login;
import repository.RecordRepository;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Camelia on 10/20/2018.
 */
public class RecordTagHandler   extends SimpleTagSupport implements DynamicAttributes {

    private String username;
    private String password;


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (username != null && password != null) {
            Login credentials = new Login(username,password);
            String email = RecordRepository.records.get(credentials.hashCode()).getEmail();
            JspWriter out = getJspContext().getOut();
            out.print("<strong> Wellcome " + username + " your email is " + email + "</strong>");
        } else {
         /* use message from the body */
            JspWriter out = getJspContext().getOut();
            out.print("Error message");
        }
    }

    @Override
    public void setDynamicAttribute(String s, String s1, Object o) throws JspException {

    }
}

