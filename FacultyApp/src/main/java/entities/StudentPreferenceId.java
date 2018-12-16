package entities;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author brusu
 */
@Embeddable
public class StudentPreferenceId {
        private static final long serialVersionUID = 1L;
    
    @Column(name="sid")
    protected Long studentId;
    @Column(name="cid")
    protected Long courseId;

    public StudentPreferenceId() {
    }

    public StudentPreferenceId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.studentId);
        hash = 79 * hash + Objects.hashCode(this.courseId);
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
        final PreferenceId other = (PreferenceId) obj;
        if (!Objects.equals(this.studentId, other.courseId)) {
            return false;
        }
        if (!Objects.equals(this.courseId, other.requiredCourseId)) {
            return false;
        }
        return true;
    }
}
