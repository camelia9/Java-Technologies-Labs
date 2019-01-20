/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Course;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author milut
 */
@Stateless
public class CourseCheckingBean {

    @PersistenceContext(name="persistanceUnit")
    private EntityManager manager;

    public Long getCourseAvailability(Course course){
        Integer availableStuds = 0;
        System.out.println("enter check availability: coursejId = " + course.getId());
        TypedQuery<Long> q1 = manager.createQuery("select c.capacity from Course c where c.id = :id", Long.class);
        q1.setParameter("id", (Long)course.getId());
        Long capacity = q1.getSingleResult();
        
        TypedQuery<Long> q2 = manager.createQuery("select c.noAssignedStudents from Course c where c.id = :id", Long.class);
        q2.setParameter("id", (Long)course.getId());
        Long noAssignedStudents = q1.getSingleResult();
        
        Long availableCapacity = capacity - noAssignedStudents;
        if(availableCapacity < 0){
            return new Long(0);
        }
        return availableCapacity;
    }
}