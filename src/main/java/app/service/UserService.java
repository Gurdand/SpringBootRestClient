package app.service;

import app.model.Role;
import app.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

//    boolean addUser(User user);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<Role> getAllRoles();

    String getURL();
}
