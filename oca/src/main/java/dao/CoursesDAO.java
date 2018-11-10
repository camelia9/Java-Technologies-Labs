package dao;


import database.Database;
import database.DatabaseConnection;
import model.Course;
import model.Lecturer;
import model.OptionalPackage;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CoursesDAO {

    private DatabaseConnection connection;
    private static final String INSERT_QUERY = String.format(
            "INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?, ?, ?)",
            database.Course.TABLE_NAME, database.Course.ABBREVIATION,database.Course.NAME,
            database.Course.STUDY_YEAR, database.Course.SEMESTER, database.Course.CREDITS_NUMBER,
            database.Course.COURSE_URL, database.Course.LECTURER
    );
    private static final String GET_ALL_COURSES = String.format(
            "SELECT * FROM %s c JOIN %s l ON c.%s = l.%s LEFT JOIN %s p ON c.%s = p.%s",
            database.Course.TABLE_NAME, database.Lecturer.TABLE_NAME, database.Course.LECTURER,
            database.Lecturer.ID, database.Package.TABLE_NAME, database.Course.PACKAGE,
            database.Package.ID
    );

    public CoursesDAO(){
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean insertCourse(Course course){
        try {
            PreparedStatement preparedStatement = this.connection.getConn().prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, course.getAbreviation());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setInt(3, course.getYearOfStudy());
            preparedStatement.setInt(4, course.getSemester());
            preparedStatement.setInt(5, course.getNumberOfCredits());
            preparedStatement.setString(6, course.getCoursePageURL());
            preparedStatement.setInt(7, course.getLecturer().getId());
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllCourses(){
        Statement stmt = null;
        List<Course> results = new LinkedList<>();
        try {
            stmt = this.connection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_COURSES);
            while(rs.next())
                results.add(new CourseBuilder().fromResultSet(rs).build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Course> getLecturerCourses(Lecturer l){
        Statement stmt = null;
        List<Course> results = new LinkedList<>();
        try {
            stmt = this.connection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "%s WHERE c.%s = %d", GET_ALL_COURSES,
                    database.Course.LECTURER, l.getId())
            );
            while(rs.next())
                results.add(
                        new CourseBuilder().setLecturer(l)
                                .fromResultSet(rs)
                                .build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Course> getPackageCourses(OptionalPackage p){
        Statement stmt = null;
        List<Course> results = new LinkedList<>();
        try {
            stmt = this.connection.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "%s WHERE c.%s = %d", GET_ALL_COURSES,
                    database.Course.PACKAGE, p.getId())
            );
            while(rs.next())
                results.add(
                        new CourseBuilder().setPackage(p)
                                .fromResultSet(rs)
                                .build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public boolean setPackage(int courseId, int packageId){
        try {
            PreparedStatement prepStmt = this.connection.getConn().prepareStatement(String.format(
                    "UPDATE %s SET %s = ? WHERE %s = ?", database.Course.TABLE_NAME,
                    database.Course.PACKAGE, database.Course.ID));
            prepStmt.setInt(1, packageId);
            prepStmt.setInt(2, courseId);
            return 1 == prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCourse(Course course){
        try {
            PreparedStatement preparedStatement = connection.getConn().prepareStatement(
                    String.format(
                        "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                        database.Course.TABLE_NAME, database.Course.ABBREVIATION, database.Course.NAME,
                        database.Course.STUDY_YEAR, database.Course.SEMESTER, database.Course.CREDITS_NUMBER,
                        database.Course.COURSE_URL, database.Course.LECTURER, database.Course.PACKAGE,
                        database.Course.ID));
            preparedStatement.setString(1, course.getAbreviation());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setInt(3, course.getYearOfStudy());
            preparedStatement.setInt(4, course.getSemester());
            preparedStatement.setInt(5, course.getNumberOfCredits());
            preparedStatement.setString(6, course.getCoursePageURL());
            preparedStatement.setInt(7, course.getLecturer().getId());
            preparedStatement.setInt(8, course.getBelongedPackage().getId());
            preparedStatement.setInt(9, course.getId());
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCourse(int id){
        try {
            PreparedStatement preparedStatement = connection.getConn().prepareStatement(
                    String.format("DELETE FROM %s WHERE %s = ?",
                            database.Course.TABLE_NAME, database.Course.ID
            ));
            preparedStatement.setInt(1, id);
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
