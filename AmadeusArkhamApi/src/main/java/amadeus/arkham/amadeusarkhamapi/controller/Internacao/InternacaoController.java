package amadeus.arkham.amadeusarkhamapi.controller.Internacao;


import amadeus.arkham.amadeusarkhamapi.application.services.Internacao.InternacaoAppService;
import amadeus.arkham.amadeusarkhamapi.application.viewmodels.Internacao.InternacaoViewModel;
import amadeus.arkham.amadeusarkhamapi.domain.models.Internacao.Internacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospitalization")
public class InternacaoController {

    private final InternacaoAppService internacaoAppService;

    public InternacaoController(InternacaoAppService internacaoAppService) {
        this.internacaoAppService = internacaoAppService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody InternacaoViewModel internacaoViewModel) {
        String response = internacaoAppService.inserirInternacao(internacaoViewModel);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Internação cadastrada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody InternacaoViewModel internacaoViewModel) {
        String response = internacaoAppService.atualizarInternacao(internacaoViewModel);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Internação atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/setDischarge")
    public ResponseEntity<String> setDischarge(@RequestBody InternacaoViewModel internacaoViewModel) {
        String response = internacaoAppService.atribuirAlta(internacaoViewModel);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Internação atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Internacao>> getAll() {
        List<Internacao> internacoess = internacaoAppService.list();
        return ResponseEntity.ok(internacoess);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody InternacaoViewModel internacaoViewModel) {
        String response =  internacaoAppService.excluirInternação(internacaoViewModel);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Internação excluída com sucesso!");
        }   else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/byDoctor/{idDoMedico}")
    public ResponseEntity<List<Internacao>> buscarPorIdDoMedico(@PathVariable Long idDoMedico) {
        List<Internacao> internacoes = internacaoAppService.buscarPorIdDoMedico(idDoMedico);
        if (internacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(internacoes);
    }


}
