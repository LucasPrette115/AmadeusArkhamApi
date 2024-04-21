package amadeus.arkham.amadeusarkhamapi.domain.models;

import jakarta.persistence.*;

@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico extends Pessoa
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String crm;
    private Boolean status;

    public Medico(final Long id,
                  String crm,
                  Boolean status)
    {
        super();
        this.id = id;
        this.crm = crm;
        this.status = status;
    }
    public Medico() {

    }

    public Long getId() {
        return id;
    }
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



