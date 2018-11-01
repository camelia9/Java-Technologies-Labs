package bean;

import dao.LecturersDAO;
import database.Database;
import model.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "lecturersBean", eager = true)
@SessionScoped
public class LecturersBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<Lecturer> allLecturers;
    public LecturersDAO lecturersDAO = new LecturersDAO();
    private String name;
    private String email;

    public LecturersBean(){
        allLecturers = lecturersDAO.getAllLecturers();
    }

    public List<Lecturer> getAllLecturers() {
        return lecturersDAO.getAllLecturers();
    }

    public String submitLecturer(){
        lecturersDAO.insertLecturer(new Lecturer(name,email));
        allLecturers = lecturersDAO.getAllLecturers();
        return "allLecturers";
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
