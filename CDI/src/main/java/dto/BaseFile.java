/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author brusu
 */
public class BaseFile {
    private int fileSize;
    private String username;
    private String fileName;
    private Date uploadDate;
    private String description;

    public BaseFile() {
        uploadDate = new Date(System.currentTimeMillis());
    }

    public BaseFile(int fileSize, String username, String fileName, String description) {
        this.fileSize = fileSize;
        this.username = username;
        this.fileName = fileName;
        this.description = description;
        uploadDate = new Date(System.currentTimeMillis());
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
