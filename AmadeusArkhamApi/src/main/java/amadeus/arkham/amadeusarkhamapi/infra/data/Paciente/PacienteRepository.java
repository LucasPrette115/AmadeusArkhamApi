package amadeus.arkham.amadeusarkhamapi.infra.data.Paciente;



import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Procedure
    void sp_insertPaciente(
            @Param("Email") String email,
            @Param("CEP") String cep,
            @Param("Cidade") String cidade,
            @Param("Numero") String numero,
            @Param("Idade") int idade,
            @Param("NomePessoa") String nomePessoa,
            @Param("Sexo") String sexo,
            @Param("Telefone") String telefone,
            @Param("CPF") String cpf,
            @Param("DataNascimento") Date dataNascimento
    );
    boolean existsByNome(String nome);
    boolean existsByCpf(String cpf);
    Paciente findByCpf(String crm);
    Paciente findById(long id);
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
}
