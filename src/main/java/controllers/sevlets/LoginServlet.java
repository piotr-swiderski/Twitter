package controllers.sevlets;

import controllers.error.ValidationError;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static controllers.utils.ServletUtils.*;

@WebServlet(name = "LoginServlet", value = {"", "/login"})
public class LoginServlet extends HttpServlet {

    private UserManagementService userService;
    private List<ValidationError> errors;
    private final int SECONDS_IN_DAY = 60 * 60 * 24;

    @Override
    public void init() throws ServletException {
        userService = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = null;
        String password = null;

        if (req != null && req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals(USER_LOGIN)) {
                    login = c.getValue();
                }
                if (c.getName().equals(USER_PASSWORD)) {
                    password = c.getValue();
                }
            }
        }

        if (login != null && password != null) {
            req.setAttribute(USER_LOGIN, login);
            req.setAttribute(USER_PASSWORD, password);
            doPost(req, resp);
            return;
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new ArrayList<>();
        String login = req.getParameter(USER_LOGIN);
        String password = req.getParameter(USER_PASSWORD);
        boolean isRememberChecked = (CHECKBOX_CHECKED).equals(req.getParameter(USER_REMEMBER));

        if (login == null && password == null) {
            login = (String) req.getAttribute(USER_LOGIN);
            password = (String) req.getAttribute(USER_PASSWORD);
        }

        if (!userService.isUserLoginExist(login)) {
            ValidationError error = new ValidationError(LOGON_LOGIN_ERROR_MESSAGE, LOGON_LOGIN_ERROR_HEADER);
            errors.add(error);
        }

        if (!userService.isUserValid(login, password)) {
            ValidationError error = new ValidationError(LOGON_PASSWORD_ERROR_MESSAGE, LOGON_LOGIN_ERROR_HEADER);
            errors.add(error);
        }

        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute(USER_LOGIN, login);

        if (isRememberChecked) {
            Cookie loginCookie = new Cookie(USER_LOGIN, login);
            Cookie passwordCookie = new Cookie(USER_PASSWORD, password);
            loginCookie.setMaxAge(SECONDS_IN_DAY);
            passwordCookie.setMaxAge(SECONDS_IN_DAY);
            resp.addCookie(loginCookie);
            resp.addCookie(passwordCookie);
        }

        req.getRequestDispatcher("users").forward(req, resp);
    }
}

