package amadeus.arkham.amadeusarkhamapi.application.services.Paciente;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Paciente.PacienteViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.infra.data.Paciente.PacienteRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas.PessoaRepository;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
                    newPatient.getDataNascimento()
            );


        } catch (ValidationException ex) {
            return ex.getMessage();
        }
        return null;
    }

    public String removerPaciente(@NotNull PacienteViewModel paciente) {
        try {
            boolean exists = pacienteRepository.existsById(paciente.getId());
            if (!exists) {
                throw new ValidationException("Paciente não encontrado");
            }
            pessoaRepository.deleteById(paciente.getId());
            pacienteRepository.deleteById(paciente.getId());

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
            Optional<Paciente> pacienteResult = pacienteRepository.findById(paciente.getId());

            if (pacienteResult.isPresent()) {
                Paciente pacienteRepo = pacienteResult.get();
                Paciente newPatient = paciente.UpdateByViewModel();
                newPatient.getPessoa().setId(pacienteRepo.getPessoa().getId());
                pessoaRepository.save(newPatient.getPessoa());
                pacienteRepository.save(newPatient);
            }
            else{
                throw new ValidationException("Paciente não encontrado!");
            }

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }

    public List<Paciente> findByNomeContainingIgnoreCase(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }
}
