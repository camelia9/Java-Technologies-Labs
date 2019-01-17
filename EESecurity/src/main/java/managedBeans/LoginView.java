/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.UserEJB;
import entities.User;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginView implements Serializable {
	private static final long serialVersionUID = 3254181235309041386L;
	private static Logger log = Logger.getLogger(LoginView.class.getName());
	@Inject
	private UserEJB userEJB;
	private String email;
	private String password;
	private User user;
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		System.out.println("[DEBUG]" + email + '\t' + password );
                try {
  
                        System.out.println("[DEBUG aproapecel mai naspa]"+userEJB.findUserById(email).getPassword());
			request.login(email, password);
                        
		} catch (ServletException e) {
                        System.out.println("[DEBUG fail exception cel mai naspa]" + e + '\t');
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
			return "signin";
		}
		Principal principal = request.getUserPrincipal();
		this.user = userEJB.findUserById(principal.getName());
		log.info("Authentication done for user: " + principal.getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("User", user);
		if (request.isUserInRole("users")) {
			return "/user/privatepage?faces-redirect=true";
		} else {
			return "signin";
		}
	}
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			this.user = null;
			request.logout();
			// clear the session
			((HttpSession) context.getExternalContext().getSession(false)).invalidate();
		} catch (ServletException e) {
			log.log(Level.SEVERE, "Failed to logout user!", e);
		}
		return "/signin?faces-redirect=true";
	}
	public User getAuthenticatedUser() {
		return user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
