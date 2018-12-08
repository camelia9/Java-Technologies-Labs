/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import repositories.StudentsRepo;

/**
 *
 * @author milut
 */

@ManagedBean(name = "studentsBean" , eager = true)
@RequestScoped
public class StudentsBean{
    
    private StudentsRepo studentsRepo;
    private List<Student> allStudents;

    public StudentsBean() {
        studentsRepo = new StudentsRepo();
        allStudents = studentsRepo.getStudents();
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public StudentsRepo getStudentsRepo() {
        return studentsRepo;
    }

    public void setStudentsRepo(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }
    
    
    
}
