/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Lecturer;
import java.util.ArrayList;
import java.util.List;
import repositories.LecturersRepo;

/**
 *
 * @author milut
 */
public class CourseSearch {
    private String name;
    private boolean isNameSelected;
    /*
    private int year;
    private boolean isYearSelected;
    
    private int semster;
    private boolean isSemesterSelected;
    
    private String lecturer;
    private List<String> allLecturers;
    private boolean isLecturerSelected;
*/
    public CourseSearch() {
        /*LecturersRepo lecturersRepo = new LecturersRepo();
        List<Lecturer> lecturers = lecturersRepo.getLecturers();
        allLecturers = new ArrayList<>();
        for(int i = 0; i < lecturers.size(); i++){
            allLecturers.add(lecturers.get(i).getName());
        }*/
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsNameSelected() {
        return isNameSelected;
    }

    public void setIsNameSelected(boolean isNameSelected) {
        this.isNameSelected = isNameSelected;
    }

    /*public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIsYearSelected() {
        return isYearSelected;
    }

    public void setIsYearSelected(boolean isYearSelected) {
        this.isYearSelected = isYearSelected;
    }

    public int getSemster() {
        return semster;
    }

    public void setSemster(int semster) {
        this.semster = semster;
    }

    public boolean isIsSemesterSelected() {
        return isSemesterSelected;
    }

    public void setIsSemesterSelected(boolean isSemesterSelected) {
        this.isSemesterSelected = isSemesterSelected;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public List<String> getAllLecturers() {
        return allLecturers;
    }

    public void setAllLecturers(List<String> allLecturers) {
        this.allLecturers = allLecturers;
    }

    public boolean isIsLecturerSelected() {
        return isLecturerSelected;
    }

    public void setIsLecturerSelected(boolean isLecturerSelected) {
        this.isLecturerSelected = isLecturerSelected;
    }
    */
    
}
