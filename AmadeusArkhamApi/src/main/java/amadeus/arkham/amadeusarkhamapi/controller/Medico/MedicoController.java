package amadeus.arkham.amadeusarkhamapi.controller.Medico;

import amadeus.arkham.amadeusarkhamapi.application.services.Medico.MedicoAppService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.CreateMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.DeleteMedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico.MedicoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/doctor")
public class MedicoController {
    private final MedicoAppService medicoAppService;


    @Autowired
    public MedicoController(MedicoAppService medicoAppService) {
        this.medicoAppService = medicoAppService;

    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateMedicoViewModel medico) throws ValidationException {
        String response = medicoAppService.salvarMedico(medico);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Médico cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody MedicoViewModel medico) throws ValidationException {
        String response = medicoAppService.atualizarMedico(medico);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Médico atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoAppService.listarMedicos();
        return ResponseEntity.ok(medicos);
    }

    @PostMapping("/getByCrm")
    public ResponseEntity<Medico> getMedicoByCrm(@RequestBody MedicoViewModel medico) throws ValidationException {
        Medico userResult = medicoAppService.buscarMedico(medico);
        if (userResult != null) {
            return ResponseEntity.ok(userResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody DeleteMedicoViewModel medico) {
        String response =  medicoAppService.removerMedico(medico);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Médico excluído com sucesso!");
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getByName")
    public List<Medico> getMedicoByName(@RequestParam String nome) {
        return medicoAppService.findByNomeContainingIgnoreCase(nome);
    }
}
