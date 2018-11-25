package beanns;

import entities.CoursePackage;
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
import repositories.PackagesRepo;

@Named("coursePackageController")
@SessionScoped
public class CoursePackageController implements Serializable {

    @EJB
    //private beanns.CoursePackageFacade ejbFacade;
    private List<CoursePackage> items = null;
    private CoursePackage selected;
    private PackagesRepo cpRepo;

    public CoursePackageController() {
        cpRepo = new PackagesRepo();
    }

    public CoursePackage getSelected() {
        return selected;
    }

    public void setSelected(CoursePackage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    /*private CoursePackageFacade getFacade() {
        return ejbFacade;
    }*/
    
    private PackagesRepo getRepo(){
        return cpRepo;
    }

    public CoursePackage prepareCreate() {
        selected = new CoursePackage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CoursePackageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CoursePackageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CoursePackageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CoursePackage> getItems() {
        if (items == null) {
            items = getRepo().getPackages();
            //items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getRepo().updatePackage(selected);
                    //getFacade().edit(selected);
                } else {
                    //getFacade().remove(selected);
                    getRepo().deletePackage(selected.getId());
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

    public CoursePackage getCoursePackage(java.lang.Long id) {
        //return getFacade().find(id);
        return getRepo().getPackage(id);
    }

    public List<CoursePackage> getItemsAvailableSelectMany() {
       // return getFacade().findAll();
        return getRepo().getPackages();
    }

    public List<CoursePackage> getItemsAvailableSelectOne() {
        //return getFacade().findAll();
        return getRepo().getPackages();
    }

    @FacesConverter(forClass = CoursePackage.class)
    public static class CoursePackageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CoursePackageController controller = (CoursePackageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "coursePackageController");
            return controller.getCoursePackage(getKey(value));
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
            if (object instanceof CoursePackage) {
                CoursePackage o = (CoursePackage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CoursePackage.class.getName()});
                return null;
            }
        }

    }

}
