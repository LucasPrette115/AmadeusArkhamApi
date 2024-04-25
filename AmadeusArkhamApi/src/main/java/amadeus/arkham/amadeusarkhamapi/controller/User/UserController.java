package amadeus.arkham.amadeusarkhamapi.controller.User;

import amadeus.arkham.amadeusarkhamapi.application.services.User.UserService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.CreateUserViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.UserViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserViewModel user) throws ValidationException {
        String response = userService.salvarUsuario(user);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listarUsuarios();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserViewModel user) {
        String response = userService.atualizarUsuario(user);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserViewModel user) {
       String response =  userService.removerUsuario(user);
       if (response == null) {
           return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
       }   else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
       }
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<List<User>> getUsers(@RequestParam String username) {
        List<User> userResult = userService.findByUsernameContainingIgnoreCase(username);
        if (userResult != null) {
            return ResponseEntity.ok(userResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
