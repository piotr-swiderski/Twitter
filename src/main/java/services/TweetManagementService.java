package services;

import model.Tweet;
import model.User;

import java.util.Set;

public interface TweetManagementService {

    void saveTweet(String userLogin, String message);

    void deleteTweet(Long tweetId);

    void updateTweet(Long tweetId, String message);

    Set<Tweet> getFollowedTweets(String login);
}
