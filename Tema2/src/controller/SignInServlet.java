package controller;

import model.Login;
import model.Record;
import model.TokenMetadata;
import repository.RecordRepository;
import utils.JspRoutes;
import utils.Routes;
import utils.SessionArgs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    private RecordRepository usersEvidence;


    @Override
    public void init() throws ServletException {
        super.init();
        usersEvidence = new RecordRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(String.format("Username: %s\tPassword: %s", username, password));

        Login login = new Login(username, password);
        Record supposedUser = usersEvidence.getUser(login);
        Record loggedInUser = (Record)request.getSession().getAttribute(SessionArgs.USER);

        if(loggedInUser == null){
            request.getSession().setAttribute(SessionArgs.USER, supposedUser);
        }
        else if (supposedUser.equals(loggedInUser)){
            // TODO sign in when there is an already signed in user
            System.out.println("[WARNING] SIGN IN WHEN THERE IS A USER SIGNED IN");
        }
        response.sendRedirect(Routes.USER_HOME_PAGE);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Record loggedInUser = (Record)request.getSession().getAttribute(SessionArgs.USER);
        if (loggedInUser == null)
            request.getRequestDispatcher(JspRoutes.SIGN_IN).forward(request, response);
        else
            response.sendRedirect(Routes.USER_HOME_PAGE);
    }
}
