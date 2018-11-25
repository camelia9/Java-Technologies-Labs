package repositories;

import entities.Course;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author brusu
 */
public class CoursesRepo extends GenericRepo<Course> {
    
    private Query selectAll;
    
    public CoursesRepo(){
        super(Course.class);
    }
    
    public List<Course> getCourses(){
        if (selectAll == null)
            selectAll = em.createQuery("SELECT c FROM Course c");
        return selectAll.getResultList();
    }
    
    public Course getCourse(long courseId){
        return getEntity(courseId);
    }
    
    public void insertCourse(Course course){
        insertEntity(course);
    }
    
    public void updateCourse(Course updatedCourse){
        updateEntity(updatedCourse);
    }
    
    public void deleteCourse(long courseId){
        deleteEntity(courseId);
    }
}
