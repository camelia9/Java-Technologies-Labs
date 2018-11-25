package beanns;

import entities.Lecturer;
import beanns.util.JsfUtil;
import beanns.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import repositories.LecturersRepo;

@ManagedBean("lecturerController")
@SessionScoped
public class LecturerController implements Serializable {

    //private beanns.LecturerFacade ejbFacade;
    private repositories.LecturersRepo lecturersRepo;
    private List<Lecturer> items = null;
    private Lecturer selected;

    public LecturerController() {
        lecturersRepo = new LecturersRepo();
    }

    public Lecturer getSelected() {
        return selected;
    }

    public void setSelected(Lecturer selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    /*private LecturerFacade getFacade() {
        return ejbFacade;
    }*/
    
    private LecturersRepo getRepo() {
        return lecturersRepo;
    }

    public Lecturer prepareCreate() {
        selected = new Lecturer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LecturerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LecturerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LecturerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Lecturer> getItems() {
        if (items == null) {
            items = getRepo().getLecturers();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getRepo().updateLecturer(selected);
                    //getFacade().edit(selected);
                } else {
                    getRepo().deleteLecturer(selected.getId());
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

    public Lecturer getLecturer(java.lang.Long id) {
        //return getFacade().find(id);
        return getRepo().getLecturer(id);
    }

    public List<Lecturer> getItemsAvailableSelectMany() {
        //return getFacade().findAll();
        return getRepo().getLecturers();
    }

    public List<Lecturer> getItemsAvailableSelectOne() {
        //return getFacade().findAll();
        return getRepo().getLecturers();
    }

    @FacesConverter(forClass = Lecturer.class)
    public static class LecturerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LecturerController controller = (LecturerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lecturerController");
            return controller.getLecturer(getKey(value));
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
            if (object instanceof Lecturer) {
                Lecturer o = (Lecturer) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Lecturer.class.getName()});
                return null;
            }
        }

    }

}
