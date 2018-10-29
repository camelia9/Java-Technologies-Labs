package bean;

import dao.LecturersDAO;
import model.Lecturer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "lecturersBean")
@SessionScoped
public class LecturersBean {
    List<Lecturer> allLecturers;
    LecturersDAO lecturersDAO;

    public LecturersBean(){
        lecturersDAO = new LecturersDAO();
        allLecturers = lecturersDAO.getAllLecturers();
    }

    public List<Lecturer> getAllLecturers() {
        return allLecturers;
    }

    public void setAllLecturers(List<Lecturer> allLecturers) {
        this.allLecturers = allLecturers;
    }


}
