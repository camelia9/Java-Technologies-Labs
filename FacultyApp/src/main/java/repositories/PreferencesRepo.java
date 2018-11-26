package repositories;

import entities.Preference;
import entities.PreferenceId;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
public class PreferencesRepo extends GenericRepo<Preference, PreferenceId> {

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
    
    public void updatePreference(Preference preference){
        updateEntity(preference);
    }
    
    public void insertPreference(Preference newPreference){
        insertEntity(newPreference);
    }
    
    public void deletePreference(PreferenceId id){
        deleteEntity(id);
    }
}
