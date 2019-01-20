/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author brusu
 */
public class LogEntry {
    
    public Date timestamp;
    public String message;
    Map<String, String> additionalData;
    
    public LogEntry(String message){
        timestamp = new Date();
        this.message = message;
        additionalData = new HashMap<>();
    }
    
    public LogEntry addAdditionalData(String what, String message){
        additionalData.put(what, message);
        return this;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(this.timestamp).append("] ");
        sb.append("Message: ").append(this.message).append("; ");
        for (Map.Entry<String, String> entry : additionalData.entrySet())
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("; ");
        sb.append('\n');
        return sb.toString();
    }
}
