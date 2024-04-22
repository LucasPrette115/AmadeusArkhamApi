package amadeus.arkham.amadeusarkhamapi.valueObjects;

import jakarta.persistence.Embeddable;


@Embeddable
public class Endereco {

    private String cep;
    private String numero;
    private String cidade;

    public Endereco(String cep, String numero, String cidade) {

        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;

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

    public Endereco() {

    }
}

