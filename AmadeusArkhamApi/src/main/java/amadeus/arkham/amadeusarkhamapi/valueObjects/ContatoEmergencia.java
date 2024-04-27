package amadeus.arkham.amadeusarkhamapi.valueObjects;


import jakarta.persistence.Embeddable;

@Embeddable
public class ContatoEmergencia {
    private String nomeContato;
    private String telefoneContato;

    public ContatoEmergencia(String nomeContato, String telefone) {
        this.nomeContato = nomeContato;
        this.telefoneContato = telefone;
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

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

}

