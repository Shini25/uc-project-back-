package finance.uc_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import finance.uc_project.model.User_account;
import finance.uc_project.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User_account> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User_account> getUserByMatricule(String matricule) {
        return userRepository.findById(matricule);
    }

    public User_account saveUser(User_account user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean matriculeExists(String matricule) {
        return userRepository.findByMatricule(matricule).isPresent();
    }

    public void deleteUser(String matricule) {
        userRepository.deleteById(matricule);
    }
}