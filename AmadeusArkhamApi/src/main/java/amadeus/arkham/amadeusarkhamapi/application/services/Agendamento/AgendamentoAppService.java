package amadeus.arkham.amadeusarkhamapi.application.services.Agendamento;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Agendamento.AgendamentoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Paciente.PacienteViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.infra.data.Agendamento.AgendamentoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoAppService {

    private final AgendamentoRepository agendamentoRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AgendamentoAppService(AgendamentoRepository agendamentoRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public String inserirAgendameto(@NotNull AgendamentoViewModel agendamento) throws ValidationException {
        Agendamentos agendamentoResp = agendamento.ByViewModel();
        Optional<Medico> medicoResult = medicoRepository.findById(agendamento.getMedicoId());
        Optional<Paciente> pacienteResult = pacienteRepository.findById(agendamento.getPacienteId());

        try {
            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }

            if (!pacienteResult.isPresent()) {
                throw new ValidationException("Paciente não encontrado");
            }
            agendamentoResp.setMedico(medicoResult.get());
            agendamentoResp.setPaciente(pacienteResult.get());
            agendamentoRepository.save(agendamentoResp);

        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }
    public List<Agendamentos> list() {
        return agendamentoRepository.findAll();
    }



    public String atualizarAgendamento(@NotNull AgendamentoViewModel agendamentoViewModel) {
        try {
            Optional<Agendamentos> agendamentosOptional = agendamentoRepository.findById(agendamentoViewModel.getId());
            Optional<Medico> medicoResult = medicoRepository.findById(agendamentoViewModel.getMedicoId());
            Optional<Paciente> pacienteResult = pacienteRepository.findById(agendamentoViewModel.getPacienteId());

            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }

            if (!pacienteResult.isPresent()) {
                throw new ValidationException("Paciente não encontrado");
            }

            if (agendamentosOptional.isPresent()) {
                Agendamentos agendamentoRepo = agendamentosOptional.get();
                Agendamentos agendamentoResult = agendamentoViewModel.ByViewModel();
                agendamentoResult.setMedico(medicoResult.get());
                agendamentoResult.setPaciente(pacienteResult.get());
                agendamentoResult.setId(agendamentoRepo.getId());
                agendamentoRepository.save(agendamentoResult);
            }
            else{
                throw new ValidationException("Agendamento não encontrado!");
            }

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public String excluirAgendamento(@NotNull AgendamentoViewModel agendamentos) {
        try {
            Optional<Agendamentos> agendamentosOptional = agendamentoRepository.findById(agendamentos.getId());
            if (!agendamentosOptional.isPresent()) {
                throw new ValidationException("Agendamento não encontrado");
            }
            agendamentoRepository.deleteById(agendamentos.getId());

        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public Agendamentos getById(Long id) {
        Optional<Agendamentos> response = agendamentoRepository.findById(id);
        return response.orElse(null);
    }
}
