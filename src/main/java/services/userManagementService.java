package services;

import model.User;

import java.util.Set;

public interface userManagementService {

    void saveUser(User user);

    void deleteUser(long userId);

    void follow(String currentUserLogin, String userLoginToFollow);

    void stopFollowing(String currentUserLogin,String userLoginToStopFollowing);

    Set<User> getNotFollowedUsers(String login);

    boolean isUserValid(String login, String password);

    boolean isUserExist(String login);

}
