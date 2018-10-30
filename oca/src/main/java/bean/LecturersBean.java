package bean;

import dao.LecturersDAO;
import database.Database;
import model.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Random;

@ManagedBean(name = "lecturersBean")
@SessionScoped
public class LecturersBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<Lecturer> allLecturers;
    public LecturersDAO lecturersDAO;
    private String name;
    private String email;

    public LecturersBean(){
        lecturersDAO = new LecturersDAO();
        allLecturers = lecturersDAO.getAllLecturers();
    }

    public List<Lecturer> getAllLecturers() {
        return lecturersDAO.getAllLecturers();
    }

    public void submitLecturer(){
        lecturersDAO.insertLecturer(new Lecturer(5,this.name,this.email));
    }

    public void insertLecturer(Lecturer lecturer) {
        lecturersDAO.insertLecturer(lecturer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



}
