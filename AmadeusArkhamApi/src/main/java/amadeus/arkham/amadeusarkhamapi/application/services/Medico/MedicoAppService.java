package amadeus.arkham.amadeusarkhamapi.application.services.Medico;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.CreateMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.DeleteMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.MedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas.PessoaRepository;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;
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
                    true,
                    newDoctor.getPessoa().getCpf(),
                    newDoctor.getPessoa().getDataNascimento()
            );


        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }
    public String atualizarMedico(@NotNull MedicoViewModel medico) {
        try {
            Optional<Medico> medicoResult = medicoRepository.findById(medico.getId());

            if (!medicoResult.isPresent()) {
                throw new ValidationException("Médico não encontrado");
            }
            Optional<Pessoa> pessoaResult = pessoaRepository.findById(medicoResult.get().getPessoa().getId());

                Medico medicoRepo = medicoResult.get();
                Pessoa pessoaRepo = pessoaResult.get();
                medicoRepo.setStatus(medico.getStatus());
                medicoRepo.setCrm(medico.getCrm());
                medicoRepo.setNome(medico.getNome());
                pessoaRepo.setIdade(medico.getIdade());
                pessoaRepo.setCpf(medico.getCpf());
                pessoaRepo.setDataNascimento(medico.getDataNascimento());
                pessoaRepo.setSexo(medico.getSexo());
                pessoaRepo.setTelefone(medico.getTelefone());
                pessoaRepo.setEmail(medico.getEmail());
                pessoaRepo.setNome(medico.getNome());
                pessoaRepo.setEndereco(new Endereco(
                                medico.getCep(),
                                medico.getNumero(),
                                medico.getCidade()
                        )
                );
                pessoaRepository.save(pessoaRepo);
                medicoRepository.save(medicoRepo);



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

    public Medico getById(Long id) {
        Optional<Medico> response = medicoRepository.findById(id);
        return response.orElse(null);
    }
}
