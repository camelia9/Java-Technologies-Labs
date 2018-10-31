package dao;

import model.Lecturer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerBuilder {

    private Lecturer lecturer;

    public LecturerBuilder(){
        this.lecturer = new Lecturer();
    }

    public LecturerBuilder fromResultSet(ResultSet rs, String prefix) throws SQLException {
        prefix = prefix == null ? "" : prefix;
        this.lecturer.setId(rs.getInt(prefix + database.Lecturer.ID));
        this.lecturer.setName(rs.getString(prefix + database.Lecturer.NAME));
        this.lecturer.setEmail(rs.getString(prefix + database.Lecturer.EMAIL));
        return this;
    }

    public LecturerBuilder setId(int id){
        this.lecturer.setId(id);
        return this;
    }

    public LecturerBuilder setName(String name){
        this.lecturer.setName(name);
        return this;
    }

    public LecturerBuilder setEmail(String email){
        this.lecturer.setEmail(email);
        return this;
    }

    public Lecturer build(){
        Lecturer ret = this.lecturer;
        this.lecturer = null;
        return ret;
    }
}
