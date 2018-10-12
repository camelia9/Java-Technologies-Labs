package controller;

import model.Record;
import repository.RecordRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.ToDoubleBiFunction;

@WebServlet(name = "RecordServlet")
public class RecordServlet extends HttpServlet {

    public RecordRepository records = new RecordRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String key = request.getParameter("key");
        String name = request.getParameter("name");
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
                records.addRecordToList(record);
                StringBuilder stringBuilder = new StringBuilder();
                for(Record r : records.getRecordList()){
                    stringBuilder.append("<tr> <td>" + r.getCategory() + " </td> <td> " + r.getKey() + " </td> <td> " + r.getName() + "</td> </tr>" );
                }
                request.setAttribute("recordList", stringBuilder.toString());
                request.getRequestDispatcher("/result.jsp").forward(request,response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
