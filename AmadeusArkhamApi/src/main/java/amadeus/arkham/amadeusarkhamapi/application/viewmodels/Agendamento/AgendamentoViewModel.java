package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Agendamento;

import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import jakarta.xml.bind.ValidationException;

import java.util.Date;
import java.util.Optional;

public class AgendamentoViewModel {
    private Long id;
    private Date dataHora;
    private String tipoConsulta;
    private Long medicoId;
    private String nomeMedico;
    private Long pacienteId;
    private String nomePaciente;


    public AgendamentoViewModel(Long id,
                                Date dataHora,
                                String tipoConsulta,
                                Long medicoId,
                                String nomeMedico,
                                Long pacienteId,
                                String nomePaciente,
                                MedicoRepository medicoRepository,
                                PacienteRepository pacienteRepository) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipoConsulta = tipoConsulta;
        this.medicoId = medicoId;
        this.nomeMedico = nomeMedico;
        this.pacienteId = pacienteId;
        this.nomePaciente = nomePaciente;

    }
    public Agendamentos ByViewModel() throws ValidationException {
        Agendamentos agendamento = new Agendamentos();
            agendamento.setId(this.id);
            agendamento.setDataHora(this.dataHora);
            agendamento.setTipoConsulta(this.tipoConsulta);
            return agendamento;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }


}
