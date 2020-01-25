package controllers.sevlets;

import controllers.utils.ServletUtils;
import model.User;
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
import java.util.Set;

import static controllers.utils.ServletUtils.*;

@WebServlet(name = "UserServlets", value = "/users")
public class UserServlets extends HttpServlet {

    private UserManagementService userManagementService;

    @Override
    public void init() throws ServletException {
        userManagementService = new UserManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        //req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        String loginAttribute = null;
        String passwordAttribute = null;

        if (req.getCookies() != null && logout != null) {
            for (Cookie c : req.getCookies()) {
                if (c.getName().equals(USER_LOGIN)) {
                    c.setMaxAge(0);
                }
                if (c.getName().equals(USER_PASSWORD)) {
                    c.setMaxAge(0);
                }
            }
            req.getRequestDispatcher("login").forward(req, resp);
        }


        String login = ServletUtils.getUserLoginFromSession(req);
        Set<User> notFollowedUser = userManagementService.getNotFollowedUsers(login);
        Set<User> followedUser = userManagementService.getFollowedUser(login);
        List<User> followedList = new ArrayList<>(followedUser);
        req.setAttribute(FOLLOWED_USERS, followedUser);
        req.setAttribute(NOT_FOLLOWED_USERS, notFollowedUser);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
