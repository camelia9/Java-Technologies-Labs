/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author brusu
 */
@WebServlet(name = "BenchmarkServlet", urlPatterns = {"/benchmark"})
public class BenchmarkServlet extends HttpServlet {

    private int connectionsCount = 0;
    DataSource dataSource;
    
     @Override
    public void init(){
        Context ctx;
        try {
            ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("jdbc/Postgres");
        } catch (NamingException ex) {
            Logger.getLogger(BenchmarkServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public synchronized Connection getPoolConnection() throws SQLException {
        connectionsCount++;
        System.out.println("DB connection number " + connectionsCount);
        return dataSource.getConnection();
    }
    
       public boolean makeSelect(Connection conn, String ipAddress, String queryString, String connType) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM info");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (connType.equals("pool")) {
                conn.close();
                System.out.println("connection closed!");
            }
        }
        return true;
    }


    public boolean makeInsert(Connection conn, String ipAddress, String queryString, String connType) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into info(remote_addr, request_params) values(?, ?)");
            stmt.setString(1, ipAddress);
            stmt.setString(2, queryString);
            stmt.executeUpdate();
            System.out.println("insert executed");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (connType.equals("pool")) {
                conn.close();
                System.out.println("connection closed!");
            }
        }
        return true;
    }
    
   
 //http://localhost:8080/OCA6/benchmark?connType=poolInsert
//http://localhost:8080/OCA6/benchmark?connType=poolSelect

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String connType = request.getParameter("connType");
        String ipAddress = request.getRemoteAddr();
        String queryString = request.getQueryString();
        boolean result = false;
        
        switch (connType){
            case "poolInsert":
                Connection conn = null;
                try {
                    conn = this.getPoolConnection();
                    result = this.makeInsert(conn, ipAddress, queryString, "pool");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "poolSelect":
                try {
                    conn = this.getPoolConnection();
                    result = this.makeSelect(conn, ipAddress, queryString, "pool");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
