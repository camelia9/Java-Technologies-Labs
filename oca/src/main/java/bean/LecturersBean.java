package bean;

import dao.LecturersDAO;
import model.Lecturer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

@ManagedBean(name = "lecturersBean", eager = true)
@RequestScoped
public class LecturersBean implements Serializable {

    private static final Pattern emailRegex =
            Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");

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

    public void validateEmail(FacesContext context,
                              UIComponent component, Object value) {
        String email = (String) value;
        if(!emailRegex.matcher(email).matches()){
            ((UIInput)component).setValid(false);
            context.addMessage(component.getClientId(context),
                    new FacesMessage("Must insert a valid email"));

        }
    }

}
