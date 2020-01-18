package dao;

import model.User;

import java.util.Set;

public interface UserDAO {

    void saveUser(User user);

    void deleteUser(long userId);

    User getUserByLogin(String login);

    User getUserByEmail(String email);

    Set<User> getNotFollowedUsers(String login);

    Set<User> getFollows(String login);

    Set<User> getFollowers(String login);



}
