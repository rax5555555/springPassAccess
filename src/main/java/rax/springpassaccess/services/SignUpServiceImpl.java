package rax.springpassaccess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rax.springpassaccess.forms.SignUpForm;
import rax.springpassaccess.models.User;
import rax.springpassaccess.repositories.UserRepository;

@RequiredArgsConstructor
@Component
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void signUpUser(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .email(form.getEmail())
                .role(User.Role.USER)
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
    }
}
