/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author brusu
 */
public class FileMemory extends BaseFile {
    private byte fileBuffer[];

    public FileMemory(byte[] fileBuffer) {
        this.fileBuffer = fileBuffer;
    }

    public FileMemory(byte[] fileBuffer, int fileSize, String username, String fileName, String description) {
        super(fileSize, username, fileName, description);
        this.fileBuffer = fileBuffer;
    }
    
    public FileMemory(BaseFile file, byte[] fileBuffer){
        super(file.getFileSize(), file.getUsername(), file.getFileName(), file.getDescription());
        this.fileBuffer = fileBuffer;
    }

    public byte[] getFileBuffer() {
        return fileBuffer;
    }

    public void setFileBuffer(byte[] fileBuffer) {
        this.fileBuffer = fileBuffer;
    }
    
    
    
    
}
