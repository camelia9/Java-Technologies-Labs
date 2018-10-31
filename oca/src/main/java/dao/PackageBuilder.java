package dao;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import model.Package;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageBuilder {

    private Package aPackage;

    public PackageBuilder(){
        this.aPackage = new Package();
    }

    public PackageBuilder fromResultSet(ResultSet rs, String prefix) throws SQLException {
        prefix = prefix == null ? "" : prefix;
        this.aPackage.setId(rs.getInt(prefix  + database.Package.ID));
        this.aPackage.setName(rs.getString(prefix + database.Package.NAME));
        this.aPackage.setYear(rs.getInt(prefix + database.Package.YEAR));
        this.aPackage.setSemester(rs.getInt(prefix + database.Package.SEMESTER));
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

    public Package build(){
        Package ret = this.aPackage;
        this.aPackage = null;
        return ret;
    }
}
