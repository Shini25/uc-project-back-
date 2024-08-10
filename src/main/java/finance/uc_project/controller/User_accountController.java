package finance.uc_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.User_account;
import finance.uc_project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class User_accountController {

    @Autowired
    private final UserService userService;

    public User_accountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User_account>> getAllUsers() {
        List<User_account> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<User_account> getUserByMatricule(@PathVariable String matricule) {
        Optional<User_account> user = userService.getUserByMatricule(matricule);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/check-matricule")
    public ResponseEntity<Boolean> checkMatriculeExists(@RequestParam String matricule) {
        boolean exists = userService.matriculeExists(matricule);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/adduser")
    public ResponseEntity<User_account> addUser(@RequestBody User_account user) {
        User_account savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<User_account> updateUser(@PathVariable String matricule, @RequestBody User_account user) {
        if (!userService.getUserByMatricule(matricule).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setMatricule(matricule); // Ensure the matricule is set correctly
        User_account updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteUser(@PathVariable String matricule) {
        if (!userService.getUserByMatricule(matricule).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(matricule);
        return ResponseEntity.noContent().build();
    }
}