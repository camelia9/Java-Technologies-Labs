/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import repositories.UsersRepo;
import utils.TrippleDes;
/**
 *
 * @author milut
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    @Inject
    UsersRepo usersRepo;
    
    private String username;
    private String password;
    private String message;

    public LoginBean() {
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

    public UsersRepo getUsersRepo() {
        return usersRepo;
    }

    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String validateEmailAndPassword() throws Exception {
        System.out.println("Bean: " + username + ", " + password);
        System.out.println("Validating...");
        TrippleDes trippleDes = new TrippleDes();
        String encryptedPas = trippleDes.encrypt(password);
        Users user = usersRepo.validateUser(username, encryptedPas);
        if (user != null) {
            System.out.println("user found: " + user.getUsername());
            
            if(user.getUserType() == "admin"){
                return "viewAllDocuments";
            }
            else {
                return "upload";
            }
            
        }
        else {
            System.out.println("user not found");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd",
                    "Please enter correct username and Password");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            message = "Incorrect email or password";
            return "login";
        }
    }
    
    public String logOut(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "login";
    }
    
    public String redirectToRegister(){
        return "register";
    }
}
