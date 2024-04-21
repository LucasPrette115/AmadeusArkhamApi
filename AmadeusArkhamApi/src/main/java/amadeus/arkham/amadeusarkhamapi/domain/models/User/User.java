package amadeus.arkham.amadeusarkhamapi.domain.models.User;

import jakarta.persistence.*;
import jakarta.xml.bind.ValidationException;


@Entity
@Table(schema = "dbo", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;


    public User(Long id, String username, String email, String password) throws ValidationException {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        validate();
    }

    public User() {
    }

    public void validate() throws ValidationException {
        if(username == null || email == null || password == null){
            throw new ValidationException("Username and email are required");
        }
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
