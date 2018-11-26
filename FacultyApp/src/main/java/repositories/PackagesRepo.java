package repositories;

import entities.CoursePackage;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
public class PackagesRepo extends GenericRepo<CoursePackage, Long> {
    
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
