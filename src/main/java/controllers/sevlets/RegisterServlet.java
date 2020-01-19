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

@WebServlet(name = "RegisterServlet", value = {"/register"})
public class RegisterServlet extends HttpServlet {

    private UserManagementService userService;
    private List<ValidationError> errors;

    @Override
    public void init() throws ServletException {
        userService = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new ArrayList<>();
        String login = req.getParameter(USER_LOGIN);
        String email = req.getParameter(USER_EMAIL);

        if (userService.isUserLoginExist(login)) {
            ValidationError error = new ValidationError(REGISTRATION_LOGIN_ERROR_MESSAGE, REGISTRATION_LOGIN_ERROR_HEADER);
            errors.add(error);
        }

        if (userService.isUserEmailExist(email)) {
            ValidationError error = new ValidationError(REGISTRATION_EMAIL_ERROR_MESSAGE, REGISTRATION_EMAIL_ERROR_HEADER);
            errors.add(error);
        }

        if(errors.size() > 0){
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        String name = req.getParameter(USER_NAME);
        String surname = req.getParameter(USER_SURNAME);
        String password = req.getParameter(USER_PASSWORD);

        userService.saveUser(User.UserBuilder
                .getBuilder()
                .withName(name)
                .withLastName(surname)
                .withLogin(login)
                .withEmail(email)
                .withPassword(password)
                .build());
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
