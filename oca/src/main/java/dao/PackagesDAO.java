package dao;

import database.Database;
import database.Package;
import model.OptionalPackage;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PackagesDAO {

    Connection connection;

    public PackagesDAO(){
        this.connection = Database.getConnection();
    }

    public boolean insertPackage(OptionalPackage aPackage){
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    String.format("INSERT INTO %s(%s, %s, %s) VALUES(?, ?, ?)",
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

    public List<OptionalPackage> getPackages(){
        List<OptionalPackage> packages = new LinkedList<>();
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

    public boolean updatePackage(OptionalPackage p){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(
                    "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                    Package.TABLE_NAME, Package.NAME, Package.YEAR,
                    Package.SEMESTER, Package.ID
            ));
            preparedStatement.setString(1, p.getName());
            preparedStatement.setInt(2, p.getYear());
            preparedStatement.setInt(3, p.getYear());
            preparedStatement.setInt(4, p.getId());
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePackage(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    String.format("DELETE FROM %s WHERE %s = ?", Package.TABLE_NAME, Package.ID)
            );
            preparedStatement.setInt(1, id);
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
