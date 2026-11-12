package br.com.tocofome.model;

public class Entregador extends Usuario {
    private boolean disponivel = true;

    public Entregador(String nome, String email) {
        super(nome, email);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void assumirEntrega(Entrega entrega) {
        disponivel = false;
        entrega.iniciar(this);
    }

    public void concluirEntrega(Entrega entrega) {
        entrega.finalizar();
        disponivel = true;
    }

    @Override
    public String getTipo() {
        return "Entregador";
    }
}
