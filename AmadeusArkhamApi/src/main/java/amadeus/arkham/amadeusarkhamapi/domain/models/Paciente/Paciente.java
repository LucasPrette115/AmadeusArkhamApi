package amadeus.arkham.amadeusarkhamapi.domain.models.Paciente;

import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.ContatoEmergencia;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
@Entity
@Table(name = "pacientes")
public class Paciente {
    @NotNull
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    private String email;
    @OneToOne
    @JoinColumn(name = "id_pessoa", unique = true)
    private Pessoa pessoa;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @NotNull
    private ContatoEmergencia contatoEmergencia;

    public Paciente(Long id,
                    String nome,
                    String cpf,
                    String email,
                    Date dataNascimento,
                    Pessoa pessoa,
                    ContatoEmergencia contatoEmergencia) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.pessoa = pessoa;
        this.contatoEmergencia = contatoEmergencia;
    }

    public Paciente() {

    }

    public ContatoEmergencia getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(ContatoEmergencia contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
