package model;

public class Course {
    private int id;
    private String abreviation;
    private String name;
    private Lecturer lecturer;
    private Integer yearOfStudy;
    private Integer semester;
    private Integer numberOfCredits;
    private String coursePageURL;
    private Package belongedPackage;


    public Course() {
    }

    public Course(int id, String abreviation, String name, Lecturer lecturer, Integer yearOfStudy,
                  Integer semester, Integer numberOfCredits, String coursePageURL) {
        this.id = id;
        this.abreviation = abreviation;
        this.name = name;
        this.lecturer = lecturer;
        this.yearOfStudy = yearOfStudy;
        this.semester = semester;
        this.numberOfCredits = numberOfCredits;
        this.coursePageURL = coursePageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
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

    public String getCoursePageURL() {
        return coursePageURL;
    }

    public void setCoursePageURL(String coursePageURL) {
        this.coursePageURL = coursePageURL;
    }

    public Package getBelongedPackage() {
        return belongedPackage;
    }

    public void setBelongedPackage(Package belongedPackage) {
        this.belongedPackage = belongedPackage;
    }

}