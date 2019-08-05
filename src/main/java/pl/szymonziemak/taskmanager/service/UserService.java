package pl.szymonziemak.taskmanager.service;

import org.apache.commons.codec.digest.DigestUtils;
import pl.szymonziemak.taskmanager.dao.DaoFactory;
import pl.szymonziemak.taskmanager.dao.UserDAO;
import pl.szymonziemak.taskmanager.model.User;

public class UserService {
    public void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String digestedPass = DigestUtils.md5Hex(password);
        user.setPassword(digestedPass);
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }

    public User getUserById(long userId) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.read(userId);
        return user;
    }

    public User getUserByUsername(String username) {
        DaoFactory factory = DaoFactory.getDaoFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByUsername(username);
        return user;
    }
}
