package bean;

import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "lecturersBean")
@SessionScoped
public class CoursesBean {
    private Object allLecturers;
    private MethodExpression addLecturer;

    public Object getAllLecturers() {
        return allLecturers;
    }

    public MethodExpression getAddLecturer() {
        return addLecturer;
    }
}
