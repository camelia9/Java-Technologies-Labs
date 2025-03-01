package dao;

import model.OptionalPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageBuilder {

    private OptionalPackage aPackage;

    public PackageBuilder(){
        this.aPackage = new OptionalPackage();
    }

    public PackageBuilder fromResultSet(ResultSet rs) throws SQLException {
        this.aPackage.setId(rs.getInt(database.Package.ID));
        this.aPackage.setName(rs.getString(database.Package.NAME));
        this.aPackage.setYear(rs.getInt(database.Package.YEAR));
        this.aPackage.setSemester(rs.getInt(database.Package.SEMESTER));
        return this;
    }

    public PackageBuilder setId(int id){
        this.aPackage.setId(id);
        return this;
    }

    public PackageBuilder setName(String name){
        this.aPackage.setName(name);
        return this;
    }

    public PackageBuilder setYear(int year){
        this.aPackage.setYear(year);
        return this;
    }

    public PackageBuilder setSemester(int semester){
        this.aPackage.setSemester(semester);
        return this;
    }

    public OptionalPackage build(){
        OptionalPackage ret = this.aPackage;
        this.aPackage = null;
        return ret;
    }
}
