package controllers.sevlets;

import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controllers.utils.ServletUtils.*;

@WebServlet(value = "/followServlet")
public class FollowServlet extends HttpServlet {

    private UserManagementService userManagementService;

    @Override
    public void init() throws ServletException {
        userManagementService = new UserManagementServiceImpl();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userToFollow = req.getParameter(USER_TO_FOLLOW);
        String userToNotFollow = req.getParameter(USER_TO_NOT_FOLLOW);
        String user = (String) req.getSession().getAttribute(USER_LOGIN);

        if (userToFollow != null && user != null) {
            userManagementService.follow(user, userToFollow);
        }

        if (userToNotFollow != null && user != null) {
            userManagementService.stopFollowing(user, userToNotFollow);
        }

        req.getRequestDispatcher("users").forward(req, resp);
    }


}
