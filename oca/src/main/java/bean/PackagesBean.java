package bean;

import dao.PackagesDAO;
import model.OptionalPackage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "packagesBean",eager = true)
@SessionScoped
public class PackagesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int year;
    private int semester;
    public PackagesDAO packagesDAO;
    public List<OptionalPackage> allPackages;
    public OptionalPackage selectedPackage;

    public PackagesBean() {
        packagesDAO= new PackagesDAO();
        allPackages = packagesDAO.getPackages();
    }

    public String add(){
        packagesDAO.insertPackage(new OptionalPackage(name,year,semester));
        allPackages = packagesDAO.getPackages();
        return "allPackages";
    }

    public void deletePackage(OptionalPackage selectedPackage){
        packagesDAO.deletePackage(selectedPackage.getId());
    }

    public List<OptionalPackage> getAllPackages(){
        return allPackages;
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
}
