package amadeus.arkham.amadeusarkhamapi.controller.Paciente;


import amadeus.arkham.amadeusarkhamapi.application.services.Paciente.PacienteAppService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Paciente.PacienteViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.domain.models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
public class PacienteController {
    private final PacienteAppService pacienteAppService;


    @Autowired
    public PacienteController(PacienteAppService pacienteAppService) {
        this.pacienteAppService = pacienteAppService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PacienteViewModel paciente) {
        String response = pacienteAppService.salvarPaciente(paciente);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> medicos = pacienteAppService.list();
        return ResponseEntity.ok(medicos);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody PacienteViewModel paciente) {
        String response =  pacienteAppService.removerPaciente(paciente);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Paciente excluído com sucesso!");
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody PacienteViewModel paciente) {
        String response = pacienteAppService.atualizarPaciente(paciente);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Paciente atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/list")
    public Page<Paciente> getList(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return pacienteAppService.getList(pageNumber, pageSize);
    }

    @PostMapping("/getByCpf")
    public ResponseEntity<Paciente> getPacienteByCpf(@RequestBody PacienteViewModel paciente) {
        Paciente userResult = pacienteAppService.buscarCpf(paciente);
        if (userResult != null) {
            return ResponseEntity.ok(userResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByName")
    public List<Paciente> getPacientesByName(@RequestParam String nome) {
        return pacienteAppService.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/getById")
    public ResponseEntity<Paciente> getById(@RequestParam Long id) {
        Paciente response =  pacienteAppService.getById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }



}
