package controller;

import model.Login;
import model.Record;
import model.TokenMetadata;
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
import java.util.HashMap;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    private final static String TOKEN_NAME = "userToken";
    private final static int COOKIE_AGE = 120;
    private RecordRepository usersEvidence;
    private HashMap<String, TokenMetadata> onlineTokens;


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
            request.setAttribute("error", "Login failed");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }else{
            String token = generateCookie(login);
            request.setAttribute("user", record);
            Cookie c = new Cookie(TOKEN_NAME, token);
            c.setMaxAge(COOKIE_AGE);
            response.addCookie(c);
            onlineTokens.put(
                    token,
                    new TokenMetadata(
                            record.hashCode(),
                            System.currentTimeMillis() + COOKIE_AGE * 1000
                    )
            );
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        for (Cookie c: cookies){
            if ( c.getName().equals(TOKEN_NAME) &&
                 onlineTokens.containsKey(c.getValue())){
                if (onlineTokens.get(c.getValue()).getExpireDate() < System.currentTimeMillis())
                    break;
                request.setAttribute(
                        "user",
                        usersEvidence.getUser(onlineTokens.get(c.getValue()).getUserDatahashCode())
                );
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                return;
            }
        }
        onlineTokens.values().removeIf(t -> t.getExpireDate() < System.currentTimeMillis());
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}
