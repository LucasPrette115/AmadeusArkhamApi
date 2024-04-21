package amadeus.arkham.amadeusarkhamapi.application.services;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.CreateUserViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.UserViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import amadeus.arkham.amadeusarkhamapi.infra.data.User.UserRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String salvarUsuario(@NotNull CreateUserViewModel user) throws ValidationException {
        User newUser = user.ByViewModel();
        try {
            boolean userExistsByEmail = userRepository.existsByEmail(newUser.getEmail());
            boolean userExistsByUsername = userRepository.existsByUsername(newUser.getUsername());

            if (userExistsByEmail || userExistsByUsername) {
                throw new ValidationException("Já existe um usuário com o mesmo email ou username");
            }
            String username = newUser.getUsername();
            String password = newUser.getPassword();
            String email = newUser.getEmail();

            if (StringUtils.containsWhitespace(username) || StringUtils.containsWhitespace(password) || StringUtils.containsWhitespace(email)) {
                throw new ValidationException("Formato incorreto!");
            }
            userRepository.insertUser(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());

        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }
    public User buscarUserporUsername(@NotNull UserViewModel user) {
        User userResult = userRepository.findByUsername(user.getUsername());
        try {
            if (userResult == null) {
                throw new ValidationException("Usuário não encontrado!");
            }

        } catch (ValidationException e) {
             e.getMessage();
        }
        return userResult;
    }
    public String atualizarUsuario(@NotNull UserViewModel user) {
        try {
            User userResult = userRepository.findByUsername(user.getUsername());
            if(userResult == null) {
                throw new ValidationException("Usuário não encontrado!");
            }
            userResult.setPassword(user.getPassword());
            userResult.setUsername(user.getUsername());
            userRepository.save(userResult);

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public String removerUsuario(@NotNull UserViewModel user) {
        User userResult = userRepository.findByUsername(user.getUsername());
        try {
            if(userResult == null) {
                throw new ValidationException("Usuário não encontrado");
            }
            userRepository.delete(userResult);
        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public boolean authenticate(@NotNull UserViewModel user) throws ValidationException {
        User userResult = userRepository.findByUsername(user.getUsername());
        return userResult != null && userResult.getPassword().equals(user.getPassword());
    }
}
