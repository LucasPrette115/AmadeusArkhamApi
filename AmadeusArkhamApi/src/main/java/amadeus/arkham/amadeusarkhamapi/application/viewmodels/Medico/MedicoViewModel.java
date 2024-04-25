package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico;

import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;

import java.util.Date;

public class MedicoViewModel {
    private Long id;
    private String nome;
    private String crm;
    private Boolean status;
    private String email;
    private String telefone;
    private int idade;
    private String sexo;
    private String cep;
    private String numero;
    private String cidade;
    private String cpf;
    private Date dataNascimento;

    public MedicoViewModel(Long id,
                           String nome,
                           Pessoa pessoa,
                           String crm, Boolean status,
                           String email, String telefone,
                           Endereco endereco, int idade,
                           String sexo, String cep,
                           String numero,
                           String cidade, String cpf, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.status = status;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
    public Medico UpdateyByViewModel(){
        Endereco endereco = new Endereco(
                cep,
                numero,
                cidade
        );
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setCrm(crm);
        medico.setStatus(status);
        medico.setPessoa(new Pessoa(
                id,
                nome,
                email,
                telefone,
                idade,
                sexo,
                endereco,
                dataNascimento,
                cpf


        ));
        return medico;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


}
