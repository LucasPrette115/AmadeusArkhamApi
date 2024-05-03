package amadeus.arkham.amadeusarkhamapi.application.services.Internacao;


import amadeus.arkham.amadeusarkhamapi.application.services.Helpers.PaginationHelper;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Internacao.InternacaoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Internacao.Internacao;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.infra.data.Internacao.InternacaoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternacaoAppService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final InternacaoRepository internacaoRepository;

    public InternacaoAppService(MedicoRepository medicoRepository, PacienteRepository pacienteRepository, InternacaoRepository internacaoRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.internacaoRepository = internacaoRepository;
    }

    public String inserirInternacao(@NotNull InternacaoViewModel internacaoViewModel) {
        Internacao internacaoResp = internacaoViewModel.updateByViewModel();
        Optional<Medico> medicoResult = medicoRepository.findById(internacaoViewModel.getMedicoId());
        Optional<Paciente> pacienteResult = pacienteRepository.findById(internacaoViewModel.getPacienteId());

        try {
            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }

            if (!pacienteResult.isPresent()) {
                throw new ValidationException("Paciente não encontrado");
            }
            internacaoResp.setMedico(medicoResult.get());
            internacaoResp.setPaciente(pacienteResult.get());
            internacaoRepository.save(internacaoResp);

        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }
    public List<Internacao> list() {
        return internacaoRepository.findAll();
    }

    public Page<Internacao> getList(int pageNumber, int pageSize) {
        List<Internacao> all = internacaoRepository.findAll();
        PaginationHelper<Internacao> paginationHelper = new PaginationHelper<>(pageSize);
        return paginationHelper.getPage(all, pageNumber);
    }

    public String atualizarInternacao(@NotNull InternacaoViewModel interacaoViewModel) {
        try {
            Optional<Internacao> internacaoOptional = internacaoRepository.findById(interacaoViewModel.getId());
            Optional<Medico> medicoResult = medicoRepository.findById(interacaoViewModel.getMedicoId());
            Optional<Paciente> pacienteResult = pacienteRepository.findById(interacaoViewModel.getPacienteId());

            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }

            if (!pacienteResult.isPresent()) {
                throw new ValidationException("Paciente não encontrado");
            }

            if (internacaoOptional.isPresent()) {
                Internacao internacaoRepo = internacaoOptional.get();
                Internacao internacaoResult = interacaoViewModel.updateByViewModel();
                internacaoResult.setMedico(medicoResult.get());
                internacaoResult.setPaciente(pacienteResult.get());
                internacaoResult.setId(internacaoRepo.getId());
                internacaoRepository.save(internacaoResult);
            }
            else{
                throw new ValidationException("Internação não encontrada!");
            }

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }
    public String atribuirAlta(@NotNull InternacaoViewModel internacaoViewModel) {
        try {
            Optional<Internacao> internacaoOptional = internacaoRepository.findById(internacaoViewModel.getId());
            if (!internacaoOptional.isPresent()) {
                throw new ValidationException("Internação não encontrada");
            }
            Internacao internacaoRepo = internacaoOptional.get();
            internacaoRepo.setDataAlta(internacaoViewModel.getDataAlta());
            internacaoRepository.save(internacaoRepo);

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public String excluirInternação(@NotNull InternacaoViewModel internacaoViewModel) {
        try {
            Optional<Internacao> internacaoOptional = internacaoRepository.findById(internacaoViewModel.getId());
            if (!internacaoOptional.isPresent()) {
                throw new ValidationException("Internação não encontrada");
            }
            internacaoRepository.deleteById(internacaoOptional.get().getId());

        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public List<Internacao> buscarPorIdDoMedico(Long idDoMedico) {
        return internacaoRepository.findByMedicoId(idDoMedico);
    }
}
