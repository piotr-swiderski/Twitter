package controllers.sevlets;

import model.Tweet;
import services.TweetManagementService;
import services.UserManagementService;
import services.impl.TweetManagementServiceImpl;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static controllers.utils.ServletUtils.USER_LOGIN;
import static controllers.utils.ServletUtils.getUserLoginFromSession;

@WebServlet(name = "MainServlet", value = {"/posts"})
public class MainPage extends HttpServlet {

    private UserManagementService userManagementService;
    private TweetManagementService tweetManagementService;
    private List<Tweet> tweetList;

    @Override
    public void init() throws ServletException {
        userManagementService = new UserManagementServiceImpl();
        tweetManagementService = new TweetManagementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = getUserLoginFromSession(req);
        tweetList = new ArrayList<>();
        tweetList.addAll((tweetManagementService.getFollowedTweets(login)));
        req.setAttribute("tweetList", tweetList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");


        String login = getUserLoginFromSession(req);
        String message = req.getParameter("message");
        tweetManagementService.saveTweet(login, message);
        tweetList = new ArrayList<>();
        tweetList.addAll((tweetManagementService.getFollowedTweets(login)));
        req.setAttribute("tweetList", tweetList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }


}
