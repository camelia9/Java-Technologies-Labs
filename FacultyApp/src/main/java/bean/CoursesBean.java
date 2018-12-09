/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Course;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.CourseSearch;
import repositories.CoursesRepo;

/**
 *
 * @author milut
 */
@Named(value = "coursesBean")
@RequestScoped
public class CoursesBean implements Serializable{
    private CoursesRepo coursesRepo;
    private List<Course> allCourses;
    private CourseSearch courseSearch;
    private List<Course> filteredCourses;

    public CoursesBean() {
        coursesRepo = new CoursesRepo();
        allCourses = coursesRepo.getCourses();
        courseSearch = new CourseSearch();
        filteredCourses = new ArrayList();
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

    public CourseSearch getCourseSearch() {
        return courseSearch;
    }

    public void setCourseSearch(CourseSearch courseSearch) {
        this.courseSearch = courseSearch;
    }
    
     public String filterCourses(){
        System.out.println("filtering");
        filteredCourses = coursesRepo.filterCourses(courseSearch);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "filteredCourses.xhtml");
        return "filteredCourses.xhtml?faces-redirect=true";
    }

    public List<Course> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<Course> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }
     
     
        
}
