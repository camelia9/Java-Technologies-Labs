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

    public Package insertPackage(Package aPackage){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    String.format("INSERT INTO %s(%s %s, %s) VALUES(?, ?, ?)",
                            database.Package.TABLE_NAME, database.Package.NAME,
                            database.Package.YEAR, database.Package.SEMESTER));

            preparedStatement.setString(1, aPackage.getName());
            preparedStatement.setInt(2, aPackage.getYear());
            preparedStatement.setInt(3, aPackage.getSemester());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()) aPackage.setId(generatedKeys.getInt(1));
                else aPackage = null;
                return aPackage;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
