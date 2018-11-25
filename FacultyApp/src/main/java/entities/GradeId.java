package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author brusu
 */
public class GradeId implements Serializable {
    
    protected Long studentId;
    protected Long courseId;

    public GradeId() {
    }

    public GradeId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.studentId);
        hash = 67 * hash + Objects.hashCode(this.courseId);
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
        final GradeId other = (GradeId) obj;
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        if (!Objects.equals(this.courseId, other.courseId)) {
            return false;
        }
        return true;
    }
    
    
}
