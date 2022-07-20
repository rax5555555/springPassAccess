package rax.springpassaccess.services;

import rax.springpassaccess.forms.UserForm;
import rax.springpassaccess.models.User;

import java.util.List;

public interface UsersService {
    void addUser(UserForm userForm);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    User getUser(Long userId);

    void updateUser(Long userId, UserForm userForm);
}
