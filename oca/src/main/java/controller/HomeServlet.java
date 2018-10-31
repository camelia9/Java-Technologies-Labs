package controller;

import config.WebRoutes;
import dao.CoursesDAO;
import dao.LecturersDAO;
import database.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "HomeServlet", urlPatterns = WebRoutes.HOME)
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CoursesDAO lecturersDAO= new CoursesDAO();
        response.getWriter().write(lecturersDAO.getAllCourses().toString());
        //RequestDispatcher rd = request.getRequestDispatcher("allLecturers.xhtml");
        //rd.forward(request, response);
    }
}
