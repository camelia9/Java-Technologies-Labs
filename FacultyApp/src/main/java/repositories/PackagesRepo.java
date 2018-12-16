package repositories;

import entities.CoursePackage;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
@Stateless
public class PackagesRepo extends GenericRepo<CoursePackage, Long> {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    Query selectAll = null;
    
   public PackagesRepo(){
       super(CoursePackage.class);
   }
   
   public List<CoursePackage> getPackages(){
       if(selectAll == null){
           selectAll = em.createQuery("SELECT p FROM CoursePackage p");
       }
       return selectAll.getResultList();
   }
   
   public CoursePackage getPackage(long packageId){
       return getEntity(packageId);
   }
   
   public void insertPackage(CoursePackage newPackage){
       insertEntity(newPackage);
   }
   
   public void updatePackage(CoursePackage updatedPackage){
       updateEntity(updatedPackage);
   }
   
   public void deletePackage(long packageId){
       deleteEntity(packageId);
   }
}
