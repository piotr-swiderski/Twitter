package dao.impl;

import dao.AbstractDao;
import dao.UserDAO;
import model.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOImpl extends AbstractDao implements UserDAO {

    @Override
    public void saveUser(User user) {
        hibernateUtil.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        hibernateUtil.delete(User.class, userId);
    }

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        query.getResultList();
        return user;
    }

    @Override
    public Set<User> getNotFollowedUsers(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.login != :login", User.class);
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        Set<User> followers = getFollowers(login);
        users.removeAll(followers);
        return new HashSet<>(users);
    }


    @Override
    public Set<User> getFollows(String login) {
        return getUserByLogin(login).getFollows();
    }

    @Override
    public Set<User> getFollowers(String login) {
        return getUserByLogin(login).getFollowers();
    }


}
