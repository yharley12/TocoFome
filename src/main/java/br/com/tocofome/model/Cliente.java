package br.com.tocofome.model;

public class Cliente extends Usuario {
    private String endereco;

    public Cliente(String nome, String email, String endereco) {
        super(nome, email);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
