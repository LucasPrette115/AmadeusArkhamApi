package amadeus.arkham.amadeusarkhamapi.infra.data.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Procedure
    void sp_insertMedico(@Param("Email") String email, @Param("CEP") String cep,
                       @Param("Cidade") String cidade, @Param("Numero") String numero,
                       @Param("Idade") int idade, @Param("NomePessoa") String nomePessoa,
                       @Param("Sexo") String sexo, @Param("Telefone") String telefone,
                       @Param("CRM") String crm, @Param("Status") boolean status);
    boolean existsByCrm(String crm);
    boolean existsByNome(String nome);
    Medico findByCrm(String crm);
}
