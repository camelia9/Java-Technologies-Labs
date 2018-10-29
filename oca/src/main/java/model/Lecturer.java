package model;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {

    private int id;
    private String name;
    private String email;
    private List<Course> courses;

    public Lecturer(){
        courses = new ArrayList<Course>();
    }

    public Lecturer(int id, String name, String email){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setProposedProjects(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getProposedProjects() {
        return courses;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
