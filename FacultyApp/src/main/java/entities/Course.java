package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author brusu
 */
@Entity
@Table(name = "courses")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @Column
    private String abbreviation;
    @Column
    private String name;
    @Column(name = "study_year")
    private int yearStudy;
    @Column
    private int semester;
    @Column(name = "credits_number")
    private int numberOfCredits;
    @Column(name = "course_url")
    private String courseURL;
    @ManyToOne
    @JoinColumn(name = "lecturer")
    private Lecturer lecturer;
    @ManyToOne
    @JoinColumn(name = "package")
    private CoursePackage coursePackage;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(Long id, String abbreviation, String name, int yearStudy, int semester, int numberOfCredits, String courseURL, Lecturer lecturer, CoursePackage coursePackage) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.yearStudy = yearStudy;
        this.semester = semester;
        this.numberOfCredits = numberOfCredits;
        this.courseURL = courseURL;
        this.lecturer = lecturer;
        this.coursePackage = coursePackage;
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearStudy() {
        return yearStudy;
    }

    public void setYearStudy(int yearStudy) {
        this.yearStudy = yearStudy;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getCourseURL() {
        return courseURL;
    }

    public void setCourseURL(String courseURL) {
        this.courseURL = courseURL;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public CoursePackage getCoursePackage() {
        return coursePackage;
    }

    public void setCoursePackage(CoursePackage coursePackage) {
        this.coursePackage = coursePackage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
