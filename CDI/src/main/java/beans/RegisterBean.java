package beans;

//import entities.UserTypeEntity;
//import entities.Users;
//import producers.UserSessionProducer;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class RegisterBean implements Serializable{
/*
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String encryptedPassword;
    private String message;

   /* @Inject
    UsersDAO usersDAO;*/

   /* @Inject
    private UserSessionProducer userSessionProducer;

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

    public String getEncryptedPassword(){
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encrypted){
        encryptedPassword = encrypted;
    }

    /*public String registerNewUser(){
        System.out.println("registering...");
        System.out.println(password);
        if (password.equals(confirmPassword)) {
            encryptPassword();
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUsername(username);
            usersEntity.setFirstName(firstName);
            usersEntity.setLastName(lastName);
            usersEntity.setPassword(encryptedPassword);
            UserTypeEntity userTypeEntity = new UserTypeEntity();
            userTypeEntity.setTypeId(2);
            userTypeEntity.setTypeName("guest");
            usersEntity.setUserType(userTypeEntity);
            usersDAO.insertNewUser(usersEntity);

            System.out.println("user found: " + usersEntity.getUsername());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            userSessionProducer.setUsersEntity(usersEntity);
            session.setAttribute("user", userSessionProducer.getUserSession());

            return "guest";
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/
}
