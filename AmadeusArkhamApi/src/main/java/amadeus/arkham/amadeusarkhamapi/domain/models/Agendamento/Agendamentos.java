package amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento;

import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Agendamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    private String tipoConsulta;

    public Agendamentos(Long id, Medico medico, Paciente paciente, Date dataHora, String tipoConsulta) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.tipoConsulta = tipoConsulta;
    }

    public Agendamentos() {
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}