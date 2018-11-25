package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author brusu
 */
public class PreferenceId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected Long courseId;
    protected Long requiredCourseId;

    public PreferenceId() {
    }

    public PreferenceId(Long courseId, Long requiredCourseId) {
        this.courseId = courseId;
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
