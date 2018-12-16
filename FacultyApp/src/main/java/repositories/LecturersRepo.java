package repositories;

import entities.Lecturer;
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
public class LecturersRepo extends GenericRepo<Lecturer, Long> {

    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    private Query selectAll;
    
    public LecturersRepo(){
        super(Lecturer.class);
    }
    
    public List<Lecturer> getLecturers(){
        if (selectAll == null)
            selectAll = em.createQuery("SELECT l FROM Lecturer l");
        return selectAll.getResultList();
    }
    
   public Lecturer getLecturer(long lecturerId){
       return getEntity(lecturerId);
   }
   
   public Lecturer getLecturerByName (String name) {
       Query q = em.createQuery("SELECT l from Lecturer l where l.name = :name");
        q.setParameter("name", name);
        Lecturer lecturer = (Lecturer) q.getSingleResult();
        return lecturer;
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void insertLecturer(Lecturer lecturer){
       insertEntity(lecturer);
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void updateLecturer(Lecturer lecturer){
       updateEntity(lecturer);
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteLecturer(long lecturerId){
       deleteEntity(lecturerId);
   }
}
