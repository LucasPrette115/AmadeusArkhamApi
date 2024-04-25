package amadeus.arkham.amadeusarkhamapi.valueObjects;


import jakarta.persistence.Embeddable;

@Embeddable
public class ContatoEmergencia {
    private String nome;
    private String telefone;

    public ContatoEmergencia(String nome, String telefone, String relacao) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public ContatoEmergencia() {

    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}

