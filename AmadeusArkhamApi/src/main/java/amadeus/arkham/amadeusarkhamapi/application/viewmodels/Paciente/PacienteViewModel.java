package amadeus.arkham.amadeusarkhamapi.application.viewmodels.Paciente;

import amadeus.arkham.amadeusarkhamapi.domain.models.Paciente.Paciente;
import amadeus.arkham.amadeusarkhamapi.domain.models.Pessoa.Pessoa;
import amadeus.arkham.amadeusarkhamapi.valueObjects.Endereco;

import java.util.Date;
import java.util.Optional;

public class PacienteViewModel {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private int idade;
    private String sexo;
    private String cep;
    private String numero;
    private String cidade;
    private Date dataNascimento;

    public PacienteViewModel(Long id,
                             String nome,
                             Pessoa pessoa,
                             String cpf,
                             String email,
                             String telefone,
                             Endereco endereco,
                             int idade,
                             String sexo,
                             String cep,
                             String numero,
                             String cidade,
                             Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
    }
    public Paciente UpdateByViewModel(){
        Endereco endereco = new Endereco(
                cep,
                numero,
                cidade
        );

        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNome(nome);
        paciente.setEmail(email);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(dataNascimento);
        paciente.setPessoa(new Pessoa(
                nome,
                email,
                telefone,
                idade,
                sexo,
                endereco,
                dataNascimento,
                cpf
        ));
        return paciente;
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


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
