package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author brusu
 */
@Embeddable
public class PreferenceId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name="course")
    protected Long courseId;
    @Column(name="required_course")
    protected Long requiredCourseId;

    public PreferenceId() {
    }

    public PreferenceId(Long courseId, Long requiredCourseId) {
        this.courseId = courseId;
        this.requiredCourseId = requiredCourseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRequiredCourseId() {
        return requiredCourseId;
    }

    public void setRequiredCourseId(Long requiredCourseId) {
        this.requiredCourseId = requiredCourseId;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.courseId);
        hash = 79 * hash + Objects.hashCode(this.requiredCourseId);
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
        if (!Objects.equals(this.courseId, other.courseId)) {
            return false;
        }
        if (!Objects.equals(this.requiredCourseId, other.requiredCourseId)) {
            return false;
        }
        return true;
    }
    
    
}
