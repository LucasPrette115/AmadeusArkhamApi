package amadeus.arkham.amadeusarkhamapi.domain.models.Internacao;

import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @NotNull
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @NotNull
    private Paciente paciente;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dataInternacao;
    @NotNull
    private String diagnostico;

    public Internacao(Long id, Medico medico, Paciente paciente, Date dataInternacao, String diagnostico) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dataInternacao = dataInternacao;
        this.diagnostico = diagnostico;
    }

    public Internacao() {

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

    public Date getDataInternacao() {
        return dataInternacao;
    }

    public void setDataInternacao(Date dataInternacao) {
        this.dataInternacao = dataInternacao;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
