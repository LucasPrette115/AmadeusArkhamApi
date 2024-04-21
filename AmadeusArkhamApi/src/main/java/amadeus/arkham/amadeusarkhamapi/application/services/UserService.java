package amadeus.arkham.amadeusarkhamapi.application.services;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.UserViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.User;
import amadeus.arkham.amadeusarkhamapi.infra.data.UserRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String salvarUsuario(UserViewModel user) throws ValidationException {
        User newUser = user.ByViewModel();
        try {
            boolean userExistsByEmail = userRepository.existsByEmail(newUser.getEmail());
            boolean userExistsByUsername = userRepository.existsByUsername(newUser.getUsername());

            if (userExistsByEmail || userExistsByUsername) {
                throw new ValidationException("Já existe um usuário com o mesmo email ou username");
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

    public void atualizarUsuario(Long id, UserViewModel updatedUserData) throws ChangeSetPersister.NotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        user.setUsername(updatedUserData.getUsername());
        user.setEmail(updatedUserData.getEmail());
        user.setPassword(updatedUserData.getPassword());
        userRepository.save(user);
    }

    public boolean authenticate(UserViewModel user) throws ValidationException {
        User userResult = userRepository.findByUsername(user.getUsername());
        return userResult != null && userResult.getPassword().equals(user.getPassword());
    }


    public User updateUserInfo(Long id, UserViewModel user) {
        return null;
    }
}
