/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.InputStream;

/**
 *
 * @author brusu
 */
public class FileStream extends BaseFile{
    
    InputStream fileStream;

    public FileStream() {
        super();
    }

    public FileStream(int fileSize, String username, String fileName, String description, InputStream fileStream) {
        super(fileSize, username, fileName, description);
        this.fileStream = fileStream;
    }
    
    public InputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }
    
    
}
