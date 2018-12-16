package repositories;

import entities.Student;
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
public class StudentsRepo extends GenericRepo<Student, Long> {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    private Query selectAll;
    
    public StudentsRepo() {
        super(Student.class);
    }
    
    public List<Student> getStudents(){
        if(selectAll == null)
            selectAll = em.createQuery("SELECT s FROM Student s");
        return selectAll.getResultList();
    }
    
    public Student getStudent(long studentId){
        return getEntity(studentId);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertStudent(Student student){
        insertEntity(student);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateStudent(Student student){
        updateEntity(student);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteStudent(long studentId){
        deleteEntity(studentId);
    }
}
