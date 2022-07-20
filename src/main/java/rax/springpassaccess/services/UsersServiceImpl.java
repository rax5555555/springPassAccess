package rax.springpassaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rax.springpassaccess.forms.UserForm;
import rax.springpassaccess.models.User;
import rax.springpassaccess.repositories.UserRepository;

import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserForm userForm) {
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .build();
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    public void updateUser(Long userId, UserForm userForm) {
        User user = userRepository.getById(userId);
        user.setFirstName(userForm.getFirstName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        userRepository.save(user);
    }
}
