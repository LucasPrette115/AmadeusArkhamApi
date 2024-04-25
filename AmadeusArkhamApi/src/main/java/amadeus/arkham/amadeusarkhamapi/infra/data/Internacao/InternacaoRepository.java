package amadeus.arkham.amadeusarkhamapi.infra.data.Internacao;

import amadeus.arkham.amadeusarkhamapi.domain.models.Internacao.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Integer> {
    Optional<Internacao> findById(Long id);
    void deleteById(Long id);

    List<Internacao> findByMedicoId(Long idDoMedico);
}
