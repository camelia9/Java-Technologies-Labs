/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Documents;
import java.io.*;
import java.sql.Date;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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
    
    public void save(){
        String fileName = file.getFileName();
        int fileSize = (int)file.getSize();
        System.out.println("[DEBUG] BEGIN");
        try (InputStream input = file.getInputstream()) {
            byte fileBuffer[] = new byte[fileSize];
            int bytesRead = input.read(fileBuffer);
            while(bytesRead < fileSize){
                bytesRead += input.read(fileBuffer, bytesRead, fileSize - bytesRead);
            }
            
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
            String username = (String)requestMap.get("username");
            
            if (username != null)
                docsRepo.insertDocument(
                        new Documents(
                                fileName, description, new Date(System.currentTimeMillis()), 
                                usersRepo.getUser(username), fileBuffer
                        )
                );
            else
                System.out.println("[DEBUG] OOPS, THERE IS NO USERNAME IN SESSION");
        }
        catch (IOException e) {
            // maybe show in interface
        }
    }
}

