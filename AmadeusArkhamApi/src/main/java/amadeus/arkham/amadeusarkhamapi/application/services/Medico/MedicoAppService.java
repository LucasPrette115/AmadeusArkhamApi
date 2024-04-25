package amadeus.arkham.amadeusarkhamapi.application.services.Medico;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.CreateMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.DeleteMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.MedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas.PessoaRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class MedicoAppService {

    private final MedicoRepository medicoRepository;
    private final PessoaRepository pessoaRepository;
    private final PacienteRepository pacienteRepository;


    @Autowired
    public MedicoAppService(MedicoRepository medicoRepository, PessoaRepository pessoaRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pessoaRepository = pessoaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public String salvarMedico(@NotNull CreateMedicoViewModel medico) {
        Medico newDoctor = medico.ByViewModel();
        try {
            boolean userExistsByCrm = medicoRepository.existsByCrm(newDoctor.getCrm());
            boolean userExistsByNome = medicoRepository.existsByNome(newDoctor.getNome());
            boolean userExistsByEmail = pessoaRepository.existsByEmail(newDoctor.getPessoa().getEmail());

            if (userExistsByCrm || userExistsByNome || userExistsByEmail)  {
                throw new ValidationException("Já existe um médico cadastrado com essas credenciais!");
            }

            medicoRepository.sp_insertMedico(
                    newDoctor.getPessoa().getEmail(),
                    newDoctor.getPessoa().getEndereco().getCep(),
                    newDoctor.getPessoa().getEndereco().getCidade(),
                    newDoctor.getPessoa().getEndereco().getNumero(),
                    newDoctor.getPessoa().getIdade(),
                    newDoctor.getPessoa().getNome(),
                    newDoctor.getPessoa().getSexo(),
                    newDoctor.getPessoa().getTelefone(),
                    newDoctor.getCrm(),
                    true
            );


        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }
    public String atualizarMedico(@NotNull MedicoViewModel medico) {
        try {
            Optional<Medico> medicoResult = medicoRepository.findById(medico.getId());

            if (medicoResult.isPresent()) {
                Medico medicoRepo = medicoResult.get();
                Medico newMedico = medico.UpdateyByViewModel();
                newMedico.getPessoa().setId(medicoRepo.getPessoa().getId());
                pessoaRepository.save(newMedico.getPessoa());
                medicoRepository.save(newMedico);
            }
            else{
                throw new ValidationException("Paciente não encontrado!");
            }

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public String removerMedico(@NotNull MedicoViewModel medico) {
        try {
            Optional<Medico> medicoResult = medicoRepository.findById(medico.getId());

            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }

            medicoRepository.deleteById(medicoResult.get().getId());
            pessoaRepository.deleteById(medicoResult.get().getPessoa().getId());


        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public Medico buscarMedico(MedicoViewModel medico) {
        Medico userResult = medicoRepository.findByCrm(medico.getCrm());
        try {
            if (userResult == null) {
                throw new ValidationException("Médico não encontrado!");
            }

        } catch (ValidationException e) {
            e.getMessage();
        }
        return userResult;
    }

    public List<Medico> findByNomeContainingIgnoreCase(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
