package repositories;

import entities.Student;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author brusu
 */
public class StudentsRepo extends GenericRepo<Student, Long> {
    
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
    
    public void insertStudent(Student student){
        insertEntity(student);
    }
    
    public void updateStudent(Student student){
        updateEntity(student);
    }
    
    public void deleteStudent(long studentId){
        deleteEntity(studentId);
    }
}
