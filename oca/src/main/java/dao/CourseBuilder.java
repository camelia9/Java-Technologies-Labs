package dao;

import model.Course;
import model.Lecturer;
import model.Package;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseBuilder {

    private Course course;

    public CourseBuilder(){
        this.course = new Course();
    }

    public CourseBuilder fromResultSet(ResultSet rs, String cprefix, String lprefix, String pprefix) throws SQLException {
        this.course.setAbreviation(rs.getString(cprefix + database.Course.ABBREVIATION));
        this.course.setId(rs.getInt(cprefix + database.Course.ID));
        this.course.setName(rs.getString(cprefix + database.Course.NAME));
        this.course.setYearOfStudy(rs.getInt(cprefix + database.Course.STUDY_YEAR));
        this.course.setSemester(rs.getInt(cprefix  +database.Course.SEMESTER));
        this.course.setNumberOfCredits(rs.getInt(cprefix + database.Course.CREDITS_NUMBER));
        this.course.setCoursePageURL(rs.getString(cprefix + database.Course.COURSE_URL));

        if (this.course.getLecturer() == null)
            this.course.setLecturer(new LecturerBuilder().fromResultSet(rs, lprefix).build());
        if (this.course.getBelongedPackage() == null)
            this.course.setBelongedPackage(new PackageBuilder().fromResultSet(rs, pprefix).build());

        return this;
    }

    public CourseBuilder setId(int id){
        this.course.setId(id);
        return this;
    }

    public CourseBuilder setAbbreviation(String abbrv){
        this.course.setAbreviation(abbrv);
        return this;
    }

    public CourseBuilder setName(String name){
        this.course.setName(name);
        return this;
    }

    public CourseBuilder setYearsOfStudy(int yearsOfStudy){
        this.course.setYearOfStudy(yearsOfStudy);
        return this;
    }

    public CourseBuilder setSemester(int semester){
        this.course.setSemester(semester);
        return this;
    }

    public CourseBuilder setNumberOfCredits(int noCredits){
        this.course.setNumberOfCredits(noCredits);
        return this;
    }

    public CourseBuilder setCoursePageURL(String url){
        this.course.setCoursePageURL(url);
        return this;
    }

    public CourseBuilder setLecturer(Lecturer l){
        this.course.setLecturer(l);
        return this;
    }

    public CourseBuilder setPackage(Package p){
        this.course.setBelongedPackage(p);
        return this;
    }

    public Course build(){
        Course temp = this.course;

        // invalidate current instance
        this.course = null;

        return temp;
    }
}
