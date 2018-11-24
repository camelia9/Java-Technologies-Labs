/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Lecturer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Camelia
 */
@Stateless
public class LectureresDao {
    
    @PersistenceContext private EntityManager em;

    // Stores a new guest: 
    public void persist(Lecturer course) {
        em.persist(course);
    }

    public List<Lecturer> getAllLecturers(){
        List<Lecturer> lecturersList = new ArrayList<>();
        Query q = em.createQuery("select * from Lecturers");
        lecturersList = q.getResultList();

        return lecturersList;
    }
}
