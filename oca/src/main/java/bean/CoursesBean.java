package bean;

import dao.CoursesDAO;
import model.Course;
import model.Lecturer;
import model.OptionalPackage;
import model.UrlData;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "coursesBean",eager = true)
@SessionScoped
public class CoursesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<model.Course> allCourses;
    private CoursesDAO coursesDAO = new CoursesDAO();

    private String abreviation;
    private String name;
    private Lecturer lecturer;
    private Integer yearOfStudy;
    private Integer semester;
    private Integer numberOfCredits;
    private UrlData coursePageURL;
    private OptionalPackage belongedPackage;
    private Course selectedCourse;

    public CoursesBean() {
        allCourses = new ArrayList<>(coursesDAO.getAllCourses());
    }

    public List<model.Course> getAllCourses() {
        return coursesDAO.getAllCourses();
    }

    public String addCourse(){
        coursesDAO.insertCourse(new Course(abreviation, name, lecturer, yearOfStudy,
                semester, numberOfCredits, coursePageURL.toString()));
        allCourses = coursesDAO.getAllCourses();
        return "allCourses";
    }

    public void deleteCourse(Course selectedCourse){
        coursesDAO.deleteCourse(selectedCourse.getId());
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(Integer numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public UrlData getCoursePageURL() {
        return coursePageURL;
    }

    public void setCoursePageURL(UrlData coursePageURL) {
        this.coursePageURL = coursePageURL;
    }

    public OptionalPackage getBelongedPackage() {
        return belongedPackage;
    }

    public void setBelongedPackage(OptionalPackage belongedPackage) {
        this.belongedPackage = belongedPackage;
    }
}

