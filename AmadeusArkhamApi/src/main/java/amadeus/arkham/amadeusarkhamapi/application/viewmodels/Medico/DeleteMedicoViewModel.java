package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;

public class DeleteMedicoViewModel extends MedicoViewModel {
    private Long id;

    public DeleteMedicoViewModel(Long id,
                                 String nome,
                                 Pessoa pessoa, String crm,
                                 Boolean status, String email,
                                 String telefone, Endereco endereco,
                                 int idade, String sexo, String cep,
                                 String numero,
                                 String cidade) {
        super(id,
                nome,
                pessoa,
                crm,
                status,
                email,
                telefone,
                endereco,
                idade,
                sexo,
                cep,
                numero,
                cidade);
    }

}
