package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author brusu
 */
@Entity
@Table(name="grades")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

   @EmbeddedId
   private GradeId id;
    @Column
    private Float grade;

    /*
    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName="id", insertable=false, updatable=false)
    private Student student;
    
    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName="course_id", insertable=false, updatable=false)
    private Course course;
*/
    
    public Grade() {
    }
    
    public Grade(GradeId id, Float grade) {
        this.id = id;
        this.grade = grade;
    }

    public Grade(GradeId id) {
        this.id = id;
    }

    public GradeId getId(){
        return this.id;
    }
    
    public void setId(GradeId id){
        this.id = new GradeId(id.studentId, id.courseId);
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return null; //student;
    }

    public void setStudent(Student student) {
        //this.student = student;
    }

    public Course getCourse() {
        return null; // course;
    }

    public void setCourse(Course course) {
        //this.course = course;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += this.id.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        return (this.id != null && other.id != null && this.id.equals(other));
    }
    
}
