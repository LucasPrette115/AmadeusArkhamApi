package amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private int idade;
    @NotNull
    private String sexo;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @NotNull
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Pessoa(Long id, String nome,
                  String email,
                  String telefone,
                  int idade, String sexo,
                  Endereco endereco,
                  Date dataNascimento, String cpf)
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public Pessoa() {

    }

    public Pessoa(String nome,
                  String email,
                  String telefone,
                  int idade,
                  String sexo,
                  Endereco endereco,
                  Date dataNascimento, @NotNull String cpf) {
                this.nome = nome;
                this.email = email;
                this.telefone = telefone;
                this.idade = idade;
                this.sexo = sexo;
                this.endereco = endereco;
                this.dataNascimento = dataNascimento;

        this.cpf = cpf;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}

