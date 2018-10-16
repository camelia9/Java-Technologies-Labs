package controller;

import model.Login;
import model.Record;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    private final static String tokenName = "userToken";
    private Map<Integer, Record> usersEvidence;
    private HashMap<String, Integer> onlineTokens;

    private boolean isCookieExpired(Cookie c){
        return c.getMaxAge() >= (int)(System.currentTimeMillis() / 1000);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        usersEvidence = new HashMap<>();
        onlineTokens = new HashMap<>();
    }

    private Record authenticate(Login login){
        return usersEvidence.getOrDefault(login.hashCode(), null);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(String.format("Username: %s\tPassword: %s", username, password));

        Record record = authenticate(new Login(username, password));
        if (record == null){
            // TODO send to error page
        }else{
            request.setAttribute("user", record);
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies){
            if ( c.getName().equals(tokenName) &&
                 onlineTokens.containsKey(c.getValue()) &&
                 !isCookieExpired(c)){
                request.setAttribute("user", usersEvidence.get(onlineTokens.get(c.getValue())));
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}
