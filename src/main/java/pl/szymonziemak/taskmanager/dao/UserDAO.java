package pl.szymonziemak.taskmanager.dao;

import pl.szymonziemak.taskmanager.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByUsername(String username);

}
