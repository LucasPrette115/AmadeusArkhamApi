package amadeus.arkham.amadeusarkhamapi.domain.models.User;

import jakarta.persistence.*;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(schema = "dbo", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private boolean status;


    public User(Long id,
                String username,
                String email,
                String password,
                boolean status) throws ValidationException {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        validate();
    }

    public User() {
    }

    public void validate() throws ValidationException {
        if(username == null || email == null || password == null){
            throw new ValidationException("Username and email are required");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
