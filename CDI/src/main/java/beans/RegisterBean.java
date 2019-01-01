package beans;


import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import javax.inject.Named;

@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable{

}
