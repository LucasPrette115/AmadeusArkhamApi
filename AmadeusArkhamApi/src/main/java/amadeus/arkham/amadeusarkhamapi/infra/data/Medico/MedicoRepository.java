package amadeus.arkham.amadeusarkhamapi.infra.data.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
