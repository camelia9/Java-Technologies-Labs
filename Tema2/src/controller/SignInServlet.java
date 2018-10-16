package controller;

import model.Login;
import model.Record;
import repository.RecordRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    private final static String tokenName = "userToken";
    private final static int age = 120;
    private RecordRepository usersEvidence;
    private HashMap<String, Integer> onlineTokens;

    private boolean isCookieExpired(Cookie c){
        return c.getMaxAge() <= (int)(System.currentTimeMillis() / 1000);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        usersEvidence = new RecordRepository();
        onlineTokens = new HashMap<>();
    }

    private Record getUserData(Login login){
        return usersEvidence.getUser(login);
    }

    private String generateCookie(Login login) {
        try {
            return new String(
                    Base64.getEncoder().encode(
                            MessageDigest.getInstance("SHA-256").digest(
                                    String.format("%s%d",
                                            login.getUsername(),
                                            (int)(System.currentTimeMillis() / 1000)).getBytes()))
                    , "utf8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(String.format("Username: %s\tPassword: %s", username, password));

        Login login = new Login(username, password);
        Record record = getUserData(login);
        if (record == null){
            // TODO send to error page
        }else{
            String token = generateCookie(login);
            request.setAttribute("user", record);
            Cookie c = new Cookie(tokenName, token);

            c.setMaxAge(age);
            response.addCookie(c);
            onlineTokens.put(token, record.hashCode());
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        for (Cookie c: cookies){
            System.out.println("IS COOKIE EXPIRED " + isCookieExpired(c));
            if ( c.getName().equals(tokenName) &&
                 onlineTokens.containsKey(c.getValue())){
                if(!isCookieExpired(c)) {
                    request.setAttribute("user", usersEvidence.getUser(onlineTokens.get(c.getValue())));
                    request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                    return;
                }
                onlineTokens.remove(c.getValue());
            }
        }
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}
