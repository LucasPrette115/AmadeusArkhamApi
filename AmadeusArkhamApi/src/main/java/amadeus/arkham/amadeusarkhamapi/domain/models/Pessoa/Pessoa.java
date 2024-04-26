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

    public Pessoa(@NotNull Long id, @NotNull String nome,
                  @NotNull String email,
                  @NotNull String telefone,
                  int idade, @NotNull String sexo,
                  Endereco endereco,
                  @NotNull Date dataNascimento, @NotNull String cpf)
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

    public Pessoa(@NotNull String nome,
                  @NotNull String email,
                  @NotNull String telefone,
                  int idade,
                  @NotNull String sexo,
                  Endereco endereco,
                  @NotNull Date dataNascimento, @NotNull String cpf) {
                this.nome = nome;
                this.email = email;
                this.telefone = telefone;
                this.idade = idade;
                this.sexo = sexo;
                this.endereco = endereco;
                this.dataNascimento = dataNascimento;
                this.cpf = cpf;
    }

    public @NotNull Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @NotNull String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
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

    public void setSexo(@NotNull String sexo) {
        this.sexo = sexo;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(@NotNull String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public @NotNull Long getId() {
        return id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public @NotNull String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}

