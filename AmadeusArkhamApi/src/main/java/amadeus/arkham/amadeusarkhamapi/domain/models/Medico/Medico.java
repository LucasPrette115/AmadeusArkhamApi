package amadeus.arkham.amadeusarkhamapi.domain.models.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Table(name = "medicos")
@Entity(name = "Medico")

public class Medico
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String crm;
    @NotNull
    private Boolean status;
    @OneToOne
    @JoinColumn(name = "id_Pessoa", referencedColumnName = "id")
    private Pessoa pessoa;
    public Medico(Long id,
                  String crm,
                  Boolean status)
    {
        this.id = id;
        this.crm = crm;
        this.status = status;
    }

    public Medico() {

    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCrm(String crm) {this.crm = crm;}
    public String getCrm() {
        return crm;
    }
    public Boolean getStatus() {
        return status;
    }
    public void disable()
    {
        this.status = false;
    }
    public void active()
    {
        this.status = true;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}



