package repositories;

import entities.Grade;
import entities.GradeId;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
public class GradesRepo extends GenericRepo<Grade, GradeId> {
    
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
    
    public void insertGrade(Grade grade){
        insertEntity(grade);
    }
    
    public void deleteGrade(GradeId gradeId){
        deleteEntity(gradeId);
    }
    
    public void updateGrade(Grade grade){
        updateEntity(grade);
    }
}
