package dao;

import database.Database;
import model.Lecturer;
import model.Package;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PackagesDAO {

    Connection connection;

    public PackagesDAO(){
        this.connection = Database.getConnection();
    }

    public boolean insertPackage(Package aPackage){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    String.format("INSERT INTO %s(%s %s, %s) VALUES(?, ?, ?)",
                            database.Package.TABLE_NAME, database.Package.NAME,
                            database.Package.YEAR, database.Package.SEMESTER));

            preparedStatement.setString(1, aPackage.getName());
            preparedStatement.setInt(2, aPackage.getYear());
            preparedStatement.setInt(3, aPackage.getSemester());
            return 1 == preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Package> getPackages(){
        List<Package> packages = new LinkedList<>();
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s", database.Package.TABLE_NAME));
            while (rs.next())
                packages.add(new PackageBuilder().fromResultSet(rs).build());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}
