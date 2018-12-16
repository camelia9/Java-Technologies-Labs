package repositories;

import entities.StudentPreference;
import entities.StudentPreferenceId;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;


/**
 *
 * @author brusu
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentPreferencesRepo extends GenericRepo<StudentPreference, StudentPreferenceId>  {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    private Query getStudentAllPreferences = null;
    
    public StudentPreferencesRepo(){
        super(StudentPreference.class);
    }
    
        public StudentPreference getPreference(StudentPreferenceId id){
        return getEntity(id);
    }
    
    public List<StudentPreference> getCoursePreferences(Long studentId){
        if(getStudentAllPreferences == null)
            getStudentAllPreferences = em.createQuery("FROM StudentPreference p WHERE p.id.studentId=:student");
        getStudentAllPreferences.setParameter("student", studentId);
        return getStudentAllPreferences.getResultList();
    }
        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePreference(StudentPreference preference){
        updateEntity(preference);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertPreference(StudentPreference newPreference){
        insertEntity(newPreference);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletePreference(StudentPreferenceId id){
        deleteEntity(id);
    }
}
