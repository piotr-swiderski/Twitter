package dao.impl;

import dao.AbstractDao;
import dao.TweetDAO;
import model.Tweet;
import model.User;
import sun.util.calendar.BaseCalendar;

import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TweetDAOImpl extends AbstractDao implements TweetDAO {

    @Override
    public void saveTweet(User user, String message) {
        Tweet tweet = new Tweet(user, message);
        hibernateUtil.save(tweet);
    }

    @Override
    public void deleteTweet(Long tweetId) {
        hibernateUtil.delete(Tweet.class, tweetId);
    }

    @Override
    public void updateTweet(Long tweetId, String message) {
        Tweet tweet = getTweet(tweetId);
        tweet.setMessage(message);
        tweet.setPublishedAt(new Date());
        hibernateUtil.save(tweet);
    }

    @Override
    public Set<Tweet> getUserTweets(String userLogin) {
        TypedQuery<Tweet> query = entityManager.createQuery("select t from Tweet t where t.author.login = :login", Tweet.class);
        query.setParameter("login", userLogin);
        return new HashSet<>(query.getResultList());
    }

    private Tweet getTweet(Long id) {
        return entityManager.find(Tweet.class, id);
    }
}
