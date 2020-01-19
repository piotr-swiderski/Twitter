package controllers.sevlets;

import controllers.error.ValidationError;
import model.User;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

    @Override
    public void init() throws ServletException {
        userService = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new ArrayList<>();
        String login = req.getParameter(USER_LOGIN);
        String password = req.getParameter(USER_PASSWORD);

        if (!userService.isUserLoginExist(login)) {
            ValidationError error = new ValidationError(LOGON_LOGIN_ERROR_MESSAGE, LOGON_LOGIN_ERROR_HEADER);
            errors.add(error);
        }

        if (!userService.isUserValid(login, password)) {
            ValidationError error = new ValidationError(LOGON_PASSWORD_ERROR_MESSAGE, LOGON_LOGIN_ERROR_HEADER);
            errors.add(error);
        }

        if(errors.size() > 0){
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}

