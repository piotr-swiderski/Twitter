package dao;

import dao.AbstractDao;
import model.Tweet;
import model.User;

import java.util.Set;

public interface TweetDAO {

    void saveTweet(User user, String message);

    void deleteTweet(Long tweetId);

    void updateTweet(Long tweetId, String message);

    Set<Tweet> getUserTweets(String userLogin);

}
