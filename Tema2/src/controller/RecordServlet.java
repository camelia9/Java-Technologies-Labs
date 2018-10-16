package controller;

import model.Record;
import repository.RecordRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecordServlet")
public class RecordServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecordRepository records = new RecordRepository();
        String category = request.getParameter("username");
        String key = request.getParameter("email");
        String name = request.getParameter("password");
        Record record = new Record(category, key, name);
        String captcha = (String) request.getSession().getAttribute("captcha");
        String typedCaptcha = request.getParameter("code");


        if(!captcha.equals(typedCaptcha)) {
            request.setAttribute("error", "Captcha did not matched");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
        else
        {
            if(!records.canBeAdded(record)){
                request.setAttribute("error", "Record already exists");
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
            else{
                records.addRecord(record);

                StringBuilder stringBuilder = new StringBuilder();
                for(Record r : records.getRecordList()){
                    stringBuilder.append("<tr> <td>" + r.getUsername() + " </td> <td> " + r.getEmail() +  "</td> </tr>" );
                }
                request.setAttribute("recordList", stringBuilder.toString());
                //request.getRequestDispatcher("/result.jsp").forward(request,response);
                response.sendRedirect("/signin");
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
