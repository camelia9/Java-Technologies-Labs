package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author brusu
 */
@Entity
@Table(name="preferences")
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private PreferenceId id;
    @Column(name="min_grade")
    private int minGrade;

    public Preference() {
    }

    public Preference(PreferenceId id) {
        this.id = id;
    }

    public Preference(PreferenceId id, int minGrade) {
        this.id = id;
        this.minGrade = minGrade;
    }

    public PreferenceId getId() {
        return id;
    }

    public void setId(PreferenceId id) {
        this.id = id;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Preference other = (Preference) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
