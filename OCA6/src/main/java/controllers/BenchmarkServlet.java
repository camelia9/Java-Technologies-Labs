/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
    @Resource(mappedName="jdbc/Postgres")
    DataSource dataSource;
    
    public synchronized Connection getPoolConnection() throws SQLException {
        connectionsCount++;
        System.out.println("DB connection number " + connectionsCount);
        return dataSource.getConnection();
    }
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.getWriter().write("Hello World");
        
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
