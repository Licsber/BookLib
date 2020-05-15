package site.licsber.book.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    char[] codeSequence = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random random = new Random();

        int width = 60;
        int codeCount = 4;
        int x1 = width / (codeCount + 1);
        int height = 20;
        int fontHeight = height - 2;
        int codeY = height - 4;

        BufferedImage buffImg = new
                BufferedImage(width,
                height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(8);
            int yl = random.nextInt(8);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder randomCode = new StringBuilder();
        int red, green, blue;
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(54)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x1, codeY);
            randomCode.append(strRand);
        }

        HttpSession session = request.getSession();
        session.setAttribute("validateCode", randomCode.toString());

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", out);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}