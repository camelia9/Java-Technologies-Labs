package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author brusu
 */
@Entity
@Table(name = "spreferences")
public class StudentPreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private StudentPreferenceId id;

    public StudentPreferenceId getId() {
        return id;
    }

    public void setId(StudentPreferenceId id) {
        this.id = id;
    }

    public StudentPreference() {
    }

    public StudentPreference(StudentPreferenceId id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentPreference)) {
            return false;
        }
        StudentPreference other = (StudentPreference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentPreference[ id=" + id + " ]";
    }    
}
