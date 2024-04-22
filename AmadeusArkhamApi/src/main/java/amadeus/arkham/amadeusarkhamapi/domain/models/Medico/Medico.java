package amadeus.arkham.amadeusarkhamapi.domain.models.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import jakarta.persistence.*;

@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    @OneToOne
    @JoinColumn(name = "id_pessoa", unique = true)
    private Pessoa pessoa;
    private String crm;
    private Boolean status;

    public Medico(final Long id, Pessoa pessoa,
                  String crm,
                  Boolean status)
    {
        this.id = id;
        this.pessoa = pessoa;
        this.crm = crm;
        this.status = status;
    }

    public Medico() {

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}



