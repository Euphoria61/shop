package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@RestController
public class ValidateCodeController {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 20;
    private static final int LENGTH = 4;
    private char code[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2',
            '3', '4', '5', '6', '7', '8', '9'};

    @GetMapping("/validateCode")
    public void validateCode(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {


        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
        Graphics g = image.getGraphics();
        Random rd = new Random();
        g.setColor(new Color(rd.nextInt(55) + 200, rd.nextInt(55) + 200, rd
                .nextInt(55) + 200));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(mFont);


        g.setColor(Color.black);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);


        String result = "";
        for (int i = 0; i < LENGTH; ++i) {
            result += code[rd.nextInt(code.length)];
        }
        HttpSession se = request.getSession();
        se.setAttribute("code", result);


        for (int i = 0; i < result.length(); i++) {
            g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
                    .nextInt(200)));
            g.drawString(result.charAt(i) + "", 12 * i + 1, 16);
        }


        for (int i = 0; i < 2; i++) {
            g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
                    .nextInt(200)));
            int x1 = rd.nextInt(WIDTH);
            int x2 = rd.nextInt(WIDTH);
            int y1 = rd.nextInt(HEIGHT);
            int y2 = rd.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }


        g.dispose();
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "JPEG", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
