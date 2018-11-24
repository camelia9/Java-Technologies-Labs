/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Course;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Camelia
 */
@Stateless
public class CoursesDao {

     // Injected database connection:
    @PersistenceContext private EntityManager em;

    // Stores a new guest: 
    public void persist(Course course) {
        em.persist(course);
    }
}
