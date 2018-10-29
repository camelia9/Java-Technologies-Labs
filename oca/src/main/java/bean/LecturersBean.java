package bean;

import dao.LecturersDAO;
import model.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "lecturersBean")
@SessionScoped
public class LecturersBean {
    public List<Lecturer> allLecturers;
    public LecturersDAO lecturersDAO;

    public LecturersBean(){
        lecturersDAO = new LecturersDAO();
        allLecturers = lecturersDAO.getAllLecturers();
    }

    public List<Lecturer> getAllLecturers() {
        return lecturersDAO.getAllLecturers();
    }

    public void insertLecturer(Lecturer lecturer) {
        lecturersDAO.insertLecturer(lecturer);
    }


}
