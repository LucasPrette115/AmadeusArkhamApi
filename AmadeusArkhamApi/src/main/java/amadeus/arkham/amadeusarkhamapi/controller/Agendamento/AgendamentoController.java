package amadeus.arkham.amadeusarkhamapi.controller.Agendamento;


import amadeus.arkham.amadeusarkhamapi.application.services.Agendamento.AgendamentoAppService;
import amadeus.arkham.amadeusarkhamapi.application.services.Medico.MedicoAppService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Agendamento.AgendamentoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Agendamento.Agendamentos;
import amadeus.arkham.amadeusarkhamapi.domain.models.Internacao.Internacao;
import jakarta.xml.bind.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
public class AgendamentoController {

    private final AgendamentoAppService agendamentoAppService;
    private final MedicoAppService medicoAppService;

    public AgendamentoController(AgendamentoAppService agendamentoAppService, MedicoAppService medicoAppService) {
        this.agendamentoAppService = agendamentoAppService;
        this.medicoAppService = medicoAppService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody AgendamentoViewModel agendamento) throws ValidationException {
        String response = agendamentoAppService.inserirAgendameto(agendamento);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Agendamentos>> getAll() {
        List<Agendamentos> agendamentos = agendamentoAppService.list();
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody AgendamentoViewModel agendamento) {
        String response = agendamentoAppService.atualizarAgendamento(agendamento);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Agendamento atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        String response =  agendamentoAppService.excluirAgendamento(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Agendamento excluído com sucesso!");
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Agendamentos> getMedicoById(@RequestParam Long id) {
        Agendamentos response =  agendamentoAppService.getById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/list")
    public Page<Agendamentos> getList(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return agendamentoAppService.getList(pageNumber, pageSize);
    }

    @GetMapping("/byDoctor/{idDoMedico}")
    public ResponseEntity<List<Agendamentos>> buscarPorIdDoMedico(@PathVariable Long idDoMedico) {
        List<Agendamentos> agendamentos = agendamentoAppService.buscarPorIdDoMedico(idDoMedico);
        if (agendamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agendamentos);
    }
}
