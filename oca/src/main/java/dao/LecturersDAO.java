package dao;

import database.Database;
import model.Lecturer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LecturersDAO {
    //query the database for lecturers
    private Connection connection;
    private static final String INSERT_QUERY = String.format(
      "INSERT INTO %s(%s, %s) VALUES (?, ?)",
            database.Lecturer.TABLE_NAME,
            database.Lecturer.NAME,
            database.Lecturer.EMAIL
    );
    private static final String GET_ALL_LECTURERS_QUERY = String.format(
            "SELECT * FROM %s", database.Lecturer.TABLE_NAME
    );


    public LecturersDAO() {
        connection = Database.getConnection();
    }

    public LecturersDAO(Connection connection){
        connection = connection;
    }

    public boolean insertLecturer(Lecturer lecturer){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, lecturer.getName());
            preparedStatement.setString(2, lecturer.getEmail());
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Lecturer> getAllLecturers(){
        List<Lecturer> lecturers = new LinkedList<>();
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_LECTURERS_QUERY);
            while (rs.next()){
                lecturers.add(new Lecturer(
                        rs.getInt(database.Lecturer.ID),
                        rs.getString(database.Lecturer.NAME),
                        rs.getString(database.Lecturer.EMAIL)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }

    public Lecturer getLecturer(int id){
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT * FROM %s WHERE %s = %d",
                    database.Lecturer.TABLE_NAME,
                    database.Lecturer.ID, id
                    )
            );
            if (rs.next()){
                return new Lecturer(
                        rs.getInt(database.Lecturer.ID),
                        rs.getString(database.Lecturer.NAME),
                        rs.getString(database.Lecturer.EMAIL)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
