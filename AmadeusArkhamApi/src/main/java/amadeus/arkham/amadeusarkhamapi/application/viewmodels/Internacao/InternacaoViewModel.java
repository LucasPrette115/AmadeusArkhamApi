package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Internacao;

import amadeus.arkham.amadeusarkhamapi.domain.models.Internacao.Internacao;

import java.util.Date;

public class InternacaoViewModel {
    private Long id;
    private Date dataInternacao;
    private String diagnostico;
    private Long medicoId;
    private String nomeMedico;
    private Long pacienteId;
    private String nomePaciente;
    private Date dataAlta;

    public InternacaoViewModel() {

    }

    public InternacaoViewModel(Long id,
                               Date dataInternacao,
                               Long medicoId,
                               String nomeMedico,
                               Long pacienteId,
                               String nomePaciente,
                               String diagnostico) {
        this.id = id;
        this.dataInternacao = dataInternacao;
        this.medicoId = medicoId;
        this.nomeMedico = nomeMedico;
        this.pacienteId = pacienteId;
        this.nomePaciente = nomePaciente;
        this.diagnostico = diagnostico;
    }

    public Internacao updateByViewModel(){
        Internacao internacao = new Internacao();
        internacao.setId(this.id);
        internacao.setDataInternacao(this.dataInternacao);
        internacao.setDiagnostico(this.diagnostico);
        return internacao;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInternacao() {
        return dataInternacao;
    }

    public void setDataInternacao(Date dataInternacao) {
        this.dataInternacao = dataInternacao;
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
