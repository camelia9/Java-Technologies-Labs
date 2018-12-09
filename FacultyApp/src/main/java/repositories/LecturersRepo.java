package repositories;

import entities.Lecturer;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
public class LecturersRepo extends GenericRepo<Lecturer, Long> {

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
   
   public void insertLecturer(Lecturer lecturer){
       insertEntity(lecturer);
   }
   
   public void updateLecturer(Lecturer lecturer){
       updateEntity(lecturer);
   }
   
   public void deleteLecturer(long lecturerId){
       deleteEntity(lecturerId);
   }
   
  
}
