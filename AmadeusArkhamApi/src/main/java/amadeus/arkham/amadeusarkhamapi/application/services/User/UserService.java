package amadeus.arkham.amadeusarkhamapi.application.services.User;

import amadeus.arkham.amadeusarkhamapi.application.services.Helpers.PaginationHelper;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.CreateUserViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.User.UserViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import amadeus.arkham.amadeusarkhamapi.infra.data.User.UserRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

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
            userRepository.sp_insertUser(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());

        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public Page<User> getUsersList(int pageNumber, int pageSize) {
        List<User> allUsers = userRepository.findAll();
        PaginationHelper<User> paginationHelper = new PaginationHelper<>(pageSize);
        return paginationHelper.getPage(allUsers, pageNumber);
    }

    public String atualizarUsuario(@NotNull UserViewModel user) {
        try {
            User userResult = userRepository.findByUsername(user.getUsername());
            if(userResult == null) {
                throw new ValidationException("Usuário não encontrado!");
            }
            userResult.setStatus(user.isStatus());
            userResult.setEmail(user.getEmail());
            userResult.setPassword(user.getPassword());
            userResult.setUsername(user.getUsername());
            userRepository.save(userResult);

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public String removerUsuario(@NotNull UserViewModel user) {
        Optional<User> userResult = userRepository.findById(user.getId());
        try {
            if(!userResult.isPresent()) {
                throw new ValidationException("Usuário não encontrado");
            }
            userRepository.delete(userResult.get());
        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public boolean authenticate(@NotNull UserViewModel user) throws ValidationException {
        User userResult = userRepository.findByUsername(user.getUsername());
        return userResult != null && userResult.getPassword().equals(user.getPassword());
    }

    public List<User> findByUsernameContainingIgnoreCase(@NotNull String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }
}
