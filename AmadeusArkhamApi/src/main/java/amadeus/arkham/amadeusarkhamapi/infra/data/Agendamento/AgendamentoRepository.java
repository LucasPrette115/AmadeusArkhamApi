package amadeus.arkham.amadeusarkhamapi.infra.data.Agendamento;


import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamentos, Long> {

}
