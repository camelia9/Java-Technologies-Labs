/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Lecturer;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import repositories.LecturersRepo;

/**
 *
 * @author milut
 */
@Named(value = "lecturersBean")
@RequestScoped
public class LecturersBean {
    private LecturersRepo lecturersRepo;
    private List<Lecturer> allLecturers;

    public LecturersBean() {
        lecturersRepo = new LecturersRepo();
        allLecturers = lecturersRepo.getLecturers();
    }
    
    public LecturersRepo getLecturersRepo() {
        return lecturersRepo;
    }
    
    public void setLecturersRepo(LecturersRepo lecturersRepo) {
        this.lecturersRepo = lecturersRepo;
    }

    public List<Lecturer> getAllLecturers() {
        return allLecturers;
    }

    public void setAllLecturers(List<Lecturer> allLecturers) {
        this.allLecturers = allLecturers;
    }
    
}
