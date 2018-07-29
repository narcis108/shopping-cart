package ro.ugal.licenta.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;
import ro.ugal.licenta.repository.RoleRepository;
import ro.ugal.licenta.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<Utilizator> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Utilizator findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public Utilizator saveUser(Utilizator utilizator) {
        utilizator.setPassword(passwordEncoder.encode(utilizator.getPassword()));
        utilizator.setEnabled(true);

        return userRepository.save(utilizator);
    }

    public void saveUserWithoutPassword(Utilizator utilizator) {
        userRepository.saveAndFlush(utilizator);
    }

}
