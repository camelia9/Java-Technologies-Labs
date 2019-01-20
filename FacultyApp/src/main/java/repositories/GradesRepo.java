package repositories;

import entities.Grade;
import entities.GradeId;
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
public class GradesRepo extends GenericRepo<Grade, GradeId> {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    private Query selectForStudent = null;
    private Query selectForCourse = null;
    
    public GradesRepo() {
        super(Grade.class);
    }
    
    public List<Grade> getGradesForStudent(Long studentId){
        if(selectForStudent == null)
            selectForStudent = em.createQuery("FROM Grade g WHERE g.id.studentId=:student");
        selectForStudent.setParameter("student", studentId);
        return selectForStudent.getResultList();
    }
    
    public List<Grade> getGradesForCourse(Long courseId){
        if(selectForCourse == null)
            selectForCourse = em.createQuery("FROM Grade g WHERE g.id.courseId=:course");
        selectForCourse.setParameter("course", courseId);
        return selectForCourse.getResultList();
    }
    
    public Grade getGrade(GradeId gradeId){
        return getEntity(gradeId);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertGrade(Grade grade){
        insertEntity(grade);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteGrade(GradeId gradeId){
        deleteEntity(gradeId);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateGrade(Grade grade){
        updateEntity(grade);
    }
}
