package amadeus.arkham.amadeusarkhamapi.application.services.Paciente;

import amadeus.arkham.amadeusarkhamapi.application.services.Helpers.PaginationHelper;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Paciente.PacienteViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas.PessoaRepository;
import amadeus.arkham.amadeusarkhamapi.valueObjects.ContatoEmergencia;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteAppService {

    private final PacienteRepository pacienteRepository;
    private final PessoaRepository pessoaRepository;


    @Autowired
    public PacienteAppService(PacienteRepository pacienteRepository, PessoaRepository pessoaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<Paciente> list() {
        return pacienteRepository.findAll();
    }

    public Page<Paciente> getList(int pageNumber, int pageSize) {
        List<Paciente> allPatients = pacienteRepository.findAll();
        PaginationHelper<Paciente> paginationHelper = new PaginationHelper<>(pageSize);
        return paginationHelper.getPage(allPatients, pageNumber);
    }

    public String salvarPaciente(@NotNull PacienteViewModel paciente) {
        Paciente newPatient = paciente.UpdateByViewModel();
        try {
            boolean userExistsByCpf = pacienteRepository.existsByCpf(paciente.getCpf());
            boolean userExistsByEmail = pessoaRepository.existsByEmail(newPatient.getPessoa().getEmail());
            boolean userExistsByNome = pacienteRepository.existsByNome(newPatient.getPessoa().getNome());

            if (userExistsByCpf || userExistsByEmail || userExistsByNome)  {
                throw new ValidationException("Já existe um paciente cadastrado com essas credenciais!");
            }

            pacienteRepository.sp_insertPaciente(
                    newPatient.getPessoa().getEmail(),
                    newPatient.getPessoa().getEndereco().getCep(),
                    newPatient.getPessoa().getEndereco().getCidade(),
                    newPatient.getPessoa().getEndereco().getNumero(),
                    newPatient.getPessoa().getIdade(),
                    newPatient.getPessoa().getNome(),
                    newPatient.getPessoa().getSexo(),
                    newPatient.getPessoa().getTelefone(),
                    newPatient.getCpf(),
                    newPatient.getDataNascimento(),
                    newPatient.getContatoEmergencia().getNomeContato(),
                    newPatient.getContatoEmergencia().getTelefoneContato()
            );


        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }

    public String removerPaciente(@NotNull Long id) {
        try {
            Optional<Paciente> pacienteResult = pacienteRepository.findById(id);
            if (!pacienteResult.isPresent()) {
                throw new ValidationException("Paciente não encontrado");
            }
            pacienteRepository.deleteById(pacienteResult.get().getPessoa().getId());
            pessoaRepository.deleteById(pacienteResult.get().getId());


        } catch (ValidationException e){
            return e.getMessage();
        }
        return null;
    }

    public Paciente buscarCpf(@NotNull PacienteViewModel paciente) {
        Paciente userResult = pacienteRepository.findByCpf(paciente.getCpf());
        try {
            if (userResult == null) {
                throw new ValidationException("Paciente não encontrado!");
            }

        } catch (ValidationException e) {
            e.getMessage();
        }
        return userResult;
    }

    public String atualizarPaciente(@NotNull PacienteViewModel paciente) {
        try {
            Optional<Paciente> pacienteResult  = pacienteRepository.findById(paciente.getId());

            if (!pacienteResult.isPresent()) {
                    throw new ValidationException("Paciente não encontrado!");
                }
            Optional<Pessoa> pessoaResult = pessoaRepository.findById(pacienteResult.get().getPessoa().getId());

                Paciente pacienteRepo = pacienteResult.get();
                Pessoa pessoaRepo = pessoaResult.get();

                pacienteRepo.setContatoEmergencia(new ContatoEmergencia(
                        paciente.getNomeContato(),
                        paciente.getTelefoneContato()
                ));
                pacienteRepo.setEmail(paciente.getEmail());
                pacienteRepo.setCpf(paciente.getCpf());
                pacienteRepo.setDataNascimento(paciente.getDataNascimento());
                pacienteRepo.setNome(paciente.getNome());
                pessoaRepo.setIdade(paciente.getIdade());
                pessoaRepo.setCpf(paciente.getCpf());
                pessoaRepo.setDataNascimento(paciente.getDataNascimento());
                pessoaRepo.setSexo(paciente.getSexo());
                pessoaRepo.setTelefone(paciente.getTelefone());
                pessoaRepo.setEmail(paciente.getEmail());
                pessoaRepo.setNome(paciente.getNome());
                pessoaRepo.setEndereco(new Endereco(
                                paciente.getCep(),
                                paciente.getNumero(),
                                paciente.getCidade()
                        )
                );
                pessoaRepository.save(pessoaRepo);
                pacienteRepository.save(pacienteRepo);
        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public List<Paciente> findByNomeContainingIgnoreCase(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }
    public Paciente getById(Long id) {
        Optional<Paciente> response = pacienteRepository.findById(id);
        return response.orElse(null);
    }
}
