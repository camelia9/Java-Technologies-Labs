package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author brusu
 */
@Entity
@Table(name="preferences")
@IdClass(PreferenceId.class)
public class Preference implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="course")
    private Long courseId;
    @Id
    @Column(name="required_course")
    private Long requiredCourseId;
    @Column(name="min_grade")
    private int minGrade;

}
