 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import org.hibernate.annotations.Type;
/**
 *
 * @author milut
 */

@Entity
@Table(name = "documents")
public class Documents implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "filename", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Column(name = "uploaddate")
    private Date uploadDate;
    
    @ManyToOne
    @JoinColumn(name = "uploadedby", referencedColumnName="username")
    private Users uploadedBy;
    
    @Lob
    @Column(name="file")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] document;

    public Documents() {
    }

    public Documents(String title, String description, Date uploadDate, Users uploadedBy, byte[] document) {
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
        this.uploadedBy = uploadedBy;
        this.document = document;
    }
    
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

   
    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Users user) {
        this.uploadedBy = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documents that = (Documents) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(uploadDate, that.uploadDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, uploadDate);
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

}
