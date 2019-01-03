package beans;


import entities.Users;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import repositories.UsersRepo;
import utils.TrippleDes;

@Named(value = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable{
   private String username;
    private String password;
    private String confirmPassword;
    private String encryptedPassword;
    private String firstName;
    private String lastName;
    private String message;
    @Inject
    UsersRepo usersRepo;
    
    public RegisterBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public String registerNewUser(){
        System.out.println("registering...");
        System.out.println(password);
        if (password.equals(confirmPassword)) {
            encryptPassword();
            Users user = new Users();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(encryptedPassword);
            user.setUserType("guest");
            usersRepo.insertUser(user);
            System.out.println("user found: " + user.getUsername());
            //redirect to file upload page
            return "upload";
        }
        else{
            System.out.println("user not found");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Different passwords",
                    "Please enter the same value for Password and Confirm Password fields");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            message = "Incorrect email or password";
            return "register";
        }
    }

    private void encryptPassword(){
        try {
            TrippleDes trippleDes = new TrippleDes();
            encryptedPassword = trippleDes.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}