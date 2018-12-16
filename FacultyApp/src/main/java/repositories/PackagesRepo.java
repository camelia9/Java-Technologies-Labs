package repositories;

import entities.CoursePackage;
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
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void insertPackage(CoursePackage newPackage){
       insertEntity(newPackage);
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void updatePackage(CoursePackage updatedPackage){
       updateEntity(updatedPackage);
   }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePackage(long packageId){
       deleteEntity(packageId);
   }
}
