package controllers.sevlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static controllers.utils.ServletUtils.USER_LOGIN;
import static controllers.utils.ServletUtils.USER_PASSWORD;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue());
                }
                if (cookie.getName().equals(USER_LOGIN)) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                }
                if (cookie.getName().equals(USER_PASSWORD)) {
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                }
                resp.addCookie(cookie);
            }
        }

        resp.sendRedirect("login");
    }

}

