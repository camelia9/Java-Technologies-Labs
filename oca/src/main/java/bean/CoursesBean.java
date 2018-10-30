package bean;

import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "lecturersBean")
@SessionScoped
public class CoursesBean implements Serializable {
    private Object allLecturers;
    private MethodExpression addLecturer;
    private static final long serialVersionUID = 1L;

    public Object getAllLecturers() {
        return allLecturers;
    }

    public MethodExpression getAddLecturer() {
        return addLecturer;
    }
}
