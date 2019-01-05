/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;
/**
 *
 * @author milut
 */

@Named(value = "fileUploadBean")
@SessionScoped
public class FileUploadBean implements Serializable{
    
    private UploadedFile file;
    
    public void setFile(UploadedFile file){
        this.file = file;
    }
    
    public UploadedFile getFile(){
        return this.file;
    }
    
    public void save(){
        String fileName = file.getFileName();
        int fileSize = (int)file.getSize();
        
        System.out.println("[DEBUG] FILE SIZE " + fileSize);
        System.out.println("[DEBUG] FILE NAME is " + fileName);
        try (InputStream input = file.getInputstream()) {
            byte fileBuffer[] = new byte[fileSize];
            int bytesRead = input.read(fileBuffer);
            while(bytesRead < fileSize){
                bytesRead += input.read(fileBuffer, bytesRead, fileSize - bytesRead);
            }
            System.out.println("[DEBUG] File content \n" + new String(fileBuffer));
        }
        catch (IOException e) {
            // maybe show in interface
        }
    }
}

