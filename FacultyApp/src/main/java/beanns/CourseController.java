package beanns;

import entities.Course;
import beanns.util.JsfUtil;
import beanns.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import repositories.CoursesRepo;

@Named("courseController")
@SessionScoped
public class CourseController implements Serializable {

    @EJB
    //private beanns.CourseFacade ejbFacade;
    private List<Course> items = null;
    private Course selected;
    private CoursesRepo coursesRepo;

    public CourseController() {
        coursesRepo = new CoursesRepo();
    }

    public Course getSelected() {
        return selected;
    }

    public void setSelected(Course selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

   /* private CourseFacade getFacade() {
        return ejbFacade;
    }*/
    
    private CoursesRepo getRepo() {
        return coursesRepo;
    }

    public Course prepareCreate() {
        selected = new Course();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CourseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CourseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CourseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Course> getItems() {
        
        if (items == null) {
            items = coursesRepo.getCourses();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getRepo().updateCourse(selected);
                   // getFacade().edit(selected);
                } else {
                    getRepo().deleteCourse(selected.getId());
                    //getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Course getCourse(java.lang.Long id) {
        return getRepo().getCourse(id);
       // return getFacade().find(id);
    }

    public List<Course> getItemsAvailableSelectMany() {
        return getRepo().getCourses();
        //return getFacade().findAll();
    }

    public List<Course> getItemsAvailableSelectOne() {
        //return getFacade().findAll();
        return getRepo().getCourses();
    }

    @FacesConverter(forClass = Course.class)
    public static class CourseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CourseController controller = (CourseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "courseController");
            return controller.getCourse(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Course) {
                Course o = (Course) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Course.class.getName()});
                return null;
            }
        }

    }

}
