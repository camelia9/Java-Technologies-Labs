package dao;

import database.Course;
import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CoursesDAO {

    private Connection connection;
    private static final String INSERT_QUERY = String.format(
            "INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?, ?, ?)",
            Course.TABLE_NAME, Course.ABBREVIATION, Course.NAME,
            Course.STUDY_YEAR, Course.SEMESTER, Course.CREDITS_NUMBER,
            Course.COURSE_URL, Course.LECTURER
    );

    public CoursesDAO(){
        this.connection = Database.getConnection();
    }

    public boolean insertCourse(model.Course course){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_QUERY);
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
}
