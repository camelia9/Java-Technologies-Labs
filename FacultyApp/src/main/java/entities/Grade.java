package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author brusu
 */
@Entity
@Table(name="grades")
@IdClass(GradeId.class)
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="student")
    private Long studentId;
    @Id
    @Column(name="course")
    private Long courseId;
    @Column
    private Float grade;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    public Grade() {
    }
    
    public Grade(Long studentId, Long courseId, Float grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public Grade(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0) + (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        return !(this.studentId == null || this.courseId == null || other.studentId == null || other.courseId == null ||
                !Objects.equals(this.studentId, other.studentId) || !Objects.equals(this.courseId, other.courseId));
    }
    
}
