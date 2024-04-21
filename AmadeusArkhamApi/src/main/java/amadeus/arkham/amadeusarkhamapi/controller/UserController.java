package amadeus.arkham.amadeusarkhamapi.controller;

import amadeus.arkham.amadeusarkhamapi.application.services.UserService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.UserViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.User;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserViewModel user) throws ValidationException {
        String response = userService.salvarUsuario(user);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listarUsuarios();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserViewModel user) {
        User updatedUser = userService.updateUserInfo(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserViewModel user) throws ValidationException {
        boolean isAuthenticated = userService.authenticate(user);
        if (isAuthenticated) {
            return ResponseEntity.ok("Autenticação bem-sucedida");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

}
