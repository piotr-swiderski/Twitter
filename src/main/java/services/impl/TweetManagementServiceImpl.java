package services.impl;

import dao.TweetDAO;
import dao.UserDAO;
import dao.impl.TweetDAOImpl;
import dao.impl.UserDAOImpl;
import model.Tweet;
import model.User;
import services.TweetManagementService;

import java.util.Set;

public class TweetManagementServiceImpl implements TweetManagementService {
    private TweetDAO tweetDAO;
    private UserDAO userDAO;

    public TweetManagementServiceImpl() {
        tweetDAO = new TweetDAOImpl();
        userDAO = new UserDAOImpl();
    }


    @Override
    public void saveTweet(String userLogin, String message) {
        User user = userDAO.getUserByLogin(userLogin);
        tweetDAO.saveTweet(user, message);
    }

    @Override
    public void updateTweet(Long tweetId, String message) {
        tweetDAO.updateTweet(tweetId, message);
    }

    @Override
    public void deleteTweet(Long tweetId) {
        tweetDAO.deleteTweet(tweetId);
    }

    @Override
    public Set<Tweet> getFollowedTweets(String login) {
        Set<Tweet> tweets = tweetDAO.getUserTweets(login);
        Set<User> followsUser = userDAO.getFollows(login);
        followsUser.forEach(f -> tweets.addAll(tweetDAO.getUserTweets(f.getLogin())));
        return tweets;
    }
}
