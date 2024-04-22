package amadeus.arkham.amadeusarkhamapi.application.services.Medico;

import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.CreateMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.DeleteMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.MedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.infra.data.Medico.MedicoRepository;
import amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas.PessoaRepository;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;
import jakarta.xml.bind.ValidationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MedicoAppService {

    private final MedicoRepository medicoRepository;
    private final PessoaRepository pessoaRepository;


    @Autowired
    public MedicoAppService(MedicoRepository medicoRepository, PessoaRepository pessoaRepository) {
        this.medicoRepository = medicoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public String salvarMedico(@NotNull CreateMedicoViewModel medico) throws ValidationException {
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
                    newDoctor.getPessoa().getEndereco().getCep(),
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
    public String atualizarMedico(@NotNull MedicoViewModel medico) throws ValidationException {
        try {
            boolean exists = medicoRepository.existsById(medico.getId());
            Medico medicoResp = medicoRepository.getReferenceById(medico.getId());
            if(!exists) {
                throw new ValidationException("Médico não encontrado!");
            }
            Endereco endereco = new Endereco(
                    medico.getCep(),
                    medico.getNumero(),
                    medico.getCidade()
            );
            Pessoa pessoa = new Pessoa(
                    medico.getId(),
                    medico.getNome(),
                    medico.getEmail(),
                    medico.getTelefone(),
                    medico.getIdade(),
                    medico.getSexo(),
                    endereco
            );
            medicoResp.setNome(medico.getNome());
            medicoResp.setCrm(medico.getCrm());
            medicoResp.setStatus(medico.getStatus());
            medicoResp.setPessoa(pessoa);

            pessoaRepository.save(pessoa);
            medicoRepository.save(medicoResp);

        } catch (ValidationException e) {
            return e.getMessage();
        }
        return null;
    }
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public String removerMedico(@NotNull DeleteMedicoViewModel medico) {
        try {
            boolean exists = medicoRepository.existsById(medico.getId());
            boolean exists2 = pessoaRepository.existsById(medico.getPessoa().getId());
            if (!exists && !exists2) {
                throw new ValidationException("Médico não encontrado");
            }
            pessoaRepository.deleteById(medico.getPessoa().getId());
            medicoRepository.deleteById(medico.getId());

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
}
