package services;

import hibernate.util.HibernateUtil;
import model.Tweet;
import model.User;
import services.impl.TweetManagementServiceImpl;
import services.impl.UserManagementServiceImpl;

import java.util.Set;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        HibernateUtil instance = HibernateUtil.getInstance();

        UserManagementService userManagementService = new UserManagementServiceImpl();
        TweetManagementService tweetManagementService = new TweetManagementServiceImpl();

        User user0 = User.UserBuilder.getBuilder().withLogin("user0").withPassword("user").withEmail("user0@gmail.com").withName("User").withLastName("User").build();
        User user1 = User.UserBuilder.getBuilder().withLogin("user1").withPassword("user").withEmail("user1@gmail.com").withName("User").withLastName("User").build();
        User user2 = User.UserBuilder.getBuilder().withLogin("user2").withPassword("user").withEmail("user2@gmail.com").withName("User").withLastName("User").build();
        User user3 = User.UserBuilder.getBuilder().withLogin("user3").withPassword("user").withEmail("user3@gmail.com").withName("User").withLastName("User").build();

        userManagementService.saveUser(user0);
        userManagementService.saveUser(user1);
        userManagementService.saveUser(user2);
        userManagementService.saveUser(user3);

        userManagementService.follow(user0.getLogin(), user1.getLogin());
        userManagementService.follow(user0.getLogin(), user2.getLogin());
        userManagementService.follow(user0.getLogin(), user3.getLogin());

        tweetManagementService.saveTweet(user1.getLogin(), "Hi! user1");
        tweetManagementService.saveTweet(user2.getLogin(), "Hi! user2");
        tweetManagementService.saveTweet(user0.getLogin(), "Hi! my");
        tweetManagementService.saveTweet(user3.getLogin(), "Hi! user3");
        tweetManagementService.saveTweet(user0.getLogin(), "Hi! my 2 post");
        tweetManagementService.saveTweet(user0.getLogin(), "Hi! my 3 post");
        tweetManagementService.saveTweet(user2.getLogin(), "Hi! user2 2 post");



        Set<Tweet> followedTweets = tweetManagementService.getFollowedTweets(user0.getLogin());
        followedTweets.forEach(t -> System.out.println("Published at: " + t.getPublishedAt() + " msg: " + t.getMessage()));


        Thread.sleep(3000);
        Tweet tweet = followedTweets.iterator().next();
        tweetManagementService.updateTweet(tweet.getId(), "UPDATE user1");


        Set<Tweet> followedTweets2 = tweetManagementService.getFollowedTweets(user0.getLogin());
        followedTweets2.forEach(t -> System.out.println("Published at: " + t.getPublishedAt() + " msg: " + t.getMessage()));

    }
}
