package model;

public class OptionalPackage {

    private int id;
    private String name;
    private int year;
    private int semester;

    public OptionalPackage() {
    }

    public OptionalPackage(String name, int year, int semester) {
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString(){

        return this.getName();
    }
}
