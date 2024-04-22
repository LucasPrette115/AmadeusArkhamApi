package amadeus.arkham.amadeusarkhamapi.infra.data.Pessoas;


import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByNome(String nome);
    boolean existsByEmail(String email);

}
