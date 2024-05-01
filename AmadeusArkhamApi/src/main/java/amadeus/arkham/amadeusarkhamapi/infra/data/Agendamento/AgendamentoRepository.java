package amadeus.arkham.amadeusarkhamapi.infra.data.Agendamento;


import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamentos, Long> {

    List<Agendamentos> findByMedicoId(Long idDoMedico);
}
