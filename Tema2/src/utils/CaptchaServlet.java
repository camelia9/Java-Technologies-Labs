package utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

@WebServlet(name = "CaptchaServlet")
public class CaptchaServlet extends HttpServlet {

    private String generateRandomString(int length){
        StringBuilder randomString = new StringBuilder();

        int sourceGenerator[][] = new int[][]{
            {(int)'a', ('z' - 'a') + 1},
            {(int)'A', ('Z' - 'A') + 1},
            {(int)'0', ('9' - '0') + 1}
        };

        for (int i = 0; i < length; ++i){
            int idx = ThreadLocalRandom.current().nextInt(0, 2);
            randomString.append(
                    (char)(sourceGenerator[idx][0] + ThreadLocalRandom.current().nextInt(0, sourceGenerator[idx][1])));
        }
        return randomString.toString();
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        int width = 150;
        int height = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(0, 0,
                Color.lightGray, 0, height/2, Color.gray, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(255, 255, 255));

        String captcha = generateRandomString(ThreadLocalRandom.current().nextInt(5, 10));
        request.getSession().setAttribute("captcha", captcha );

        int x = 0;
        int y = 0;

        for (int i = 0; i < captcha.length(); ++i) {
            x += 10 + ThreadLocalRandom.current().nextInt(15);
            y = 20 + ThreadLocalRandom.current().nextInt(20);
            g2d.drawChars(captcha.toCharArray(), i, 1, x, y);
        }

        g2d.dispose();

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        os.close();
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
