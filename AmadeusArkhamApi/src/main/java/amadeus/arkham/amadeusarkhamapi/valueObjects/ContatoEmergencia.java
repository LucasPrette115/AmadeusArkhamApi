package amadeus.arkham.amadeusarkhamapi.valueObjects;


import jakarta.persistence.Embeddable;

@Embeddable
public class ContatoEmergencia {
    private String nomeContato;
    private String telefone;

    public ContatoEmergencia(String nomeContato, String telefone, String relacao) {
        this.nomeContato = nomeContato;
        this.telefone = telefone;
    }

    public ContatoEmergencia() {

    }

    // Getters e Setters
    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}

