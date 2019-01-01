/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import entities.Documents;
//import entities.Users;
import interceptors.LoggerInterceptor;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 *
 * @author milut
 */

@Named(value = "fileUploadBean")
@SessionScoped
public class FileUploadBean implements Serializable{
    
}

