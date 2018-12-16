package repositories;

import entities.Course;
import entities.Course_;
import entities.Lecturer;
import interceptor.CallInterceptor;
import javax.persistence.Query;
import java.util.List;
import model.CourseSearch;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author brusu
 */
@Interceptors({CallInterceptor.class})
@Stateless
public class CoursesRepo extends GenericRepo<Course, Long> {
    
    @PersistenceContext(name="persistenceUnit") 
    private EntityManager em;
    @PersistenceUnit(unitName="persistenceUnit")
    EntityManagerFactory factory;

    private Query selectAll;
    
    public CoursesRepo(){
        super(Course.class);
    }
    
    public List<Course> getCourses(){
        if (em == null){
            System.out.println("[DEBUG] EntityManager is null");
        }
        else if (selectAll == null)
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
    
    public List<Course> filterCourses(CourseSearch courseSearch){
        CriteriaBuilder builder = factory.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = builder.createQuery(Course.class );
        Root<Course> coursesEntityRoot = criteriaQuery.from( Course.class );
        List<Predicate> predicates = new ArrayList<Predicate>();

        if(courseSearch.isIsNameSelected() && courseSearch.getName() != null){
            Expression<String> courseName = coursesEntityRoot.get(Course_.name);
            predicates.add(builder.like(builder.lower(courseName), ("%"+courseSearch.getName()+"%").toLowerCase()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Course> query = em.createQuery(criteriaQuery);
        List<Course> courses = query.getResultList();
        System.out.println(predicates.size());
        System.out.println(courses.size());
        return courses;
    }
}
