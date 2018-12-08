/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Course;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import repositories.CoursesRepo;

/**
 *
 * @author milut
 */
@Named(value = "coursesBean")
@RequestScoped
public class CoursesBean {
    private CoursesRepo coursesRepo;
    private List<Course> allCourses;

    public CoursesBean() {
        coursesRepo = new CoursesRepo();
        allCourses = coursesRepo.getCourses();
    }

    public CoursesRepo getCoursesRepo() {
        return coursesRepo;
    }

    public void setCoursesRepo(CoursesRepo coursesRepo) {
        this.coursesRepo = coursesRepo;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = allCourses;
    }
        
}
