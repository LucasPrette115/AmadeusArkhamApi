package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Medico;


import amadeus.arkham.amadeusarkhamapi.domain.models.Medico.Medico;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;

import java.util.Date;


public class CreateMedicoViewModel {
    private Long id;
    private String nome;
    private Pessoa pessoa;
    private String crm;
    private Boolean status;
    private String email;
    private String telefone;
    private Endereco endereco;
    private int idade;
    private String sexo;
    private String cep;
    private String numero;
    private String cidade;
    private Date dataNascimento;
    private String cpf;


    public CreateMedicoViewModel(Long id,
                                 String nome,
                                 Pessoa pessoa,
                                 String crm, Boolean status,
                                 String email, String telefone,
                                 Endereco endereco, int idade,
                                 String sexo, String cep,
                                 String numero,
                                 String cidade,
                                 Date dataNascimento, String cpf) {
        this.id = id;
        this.nome = nome;
        this.pessoa = pessoa;
        this.crm = crm;
        this.status = status;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idade = idade;
        this.sexo = sexo;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }


    public Medico ByViewModel(){
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

