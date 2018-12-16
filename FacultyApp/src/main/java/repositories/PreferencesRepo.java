package repositories;

import entities.Preference;
import entities.PreferenceId;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PreferencesRepo extends GenericRepo<Preference, PreferenceId> {

    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    private Query getCourseAllPreferences = null;
    
    public PreferencesRepo() {
        super(Preference.class);
    }
    
    public List<Preference> getCoursePreferences(Long courseId){
        if(getCourseAllPreferences == null)
            getCourseAllPreferences = em.createQuery("FROM Preference p WHERE p.id.courseId=:course");
        getCourseAllPreferences.setParameter("course", courseId);
        return getCourseAllPreferences.getResultList();
    }
    
    public Preference getPreference(PreferenceId id){
        return getEntity(id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePreference(Preference preference){
        updateEntity(preference);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertPreference(Preference newPreference){
        insertEntity(newPreference);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletePreference(PreferenceId id){
        deleteEntity(id);
    }
}
