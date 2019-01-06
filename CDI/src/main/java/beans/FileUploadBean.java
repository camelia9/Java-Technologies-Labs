/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dto.*;
import entities.Documents;
import interceptor.CallInterceptor;
import java.io.*;
import java.sql.Date;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import org.primefaces.model.UploadedFile;
import repositories.*;
/**
 *
 * @author milut
 */

@Named(value = "fileUploadBean")
@SessionScoped
public class FileUploadBean implements Serializable{
    @Inject
    DocumentsRepo docsRepo;
    @Inject
    UsersRepo usersRepo;
    @Inject
    private Event<LogEntry> logger;
    @Inject
    private Event<FileStream> streamer;
    

    private UploadedFile file;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setFile(UploadedFile file){
        this.file = file;
    }
    
    public UploadedFile getFile(){
        return this.file;
    }
    
    @Interceptors({CallInterceptor.class})
    public void save(){
        String fileName = file.getFileName();
        int fileSize = (int)file.getSize();
       System.out.println("[DEBUG] Directory: " + System.getProperty("user.dir"));
        try {
            InputStream input = file.getInputstream();
            
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
            String username = (String)requestMap.get("username");
            
            FileStream fileStream = new FileStream(fileSize, username, fileName, description, input);
            
            if (username != null){
                System.out.println("[DEBUG] SEND EVENT TO SAVE TO DB");
                streamer.fire(fileStream);
                LogEntry log = new LogEntry("File arrived on server")
                        .addAdditionalData("Uploaded by", username)
                        .addAdditionalData("File name", fileName)
                        .addAdditionalData("File size", String.valueOf(fileSize));
                System.out.println("[DEBUG] SEND EVENT TO LOG");
                logger.fire(log);
            }
            else
                System.out.println("[DEBUG] OOPS, THERE IS NO USERNAME IN SESSION");
        }
        catch (IOException e) {
            // maybe show in interface
        }
    }
}

