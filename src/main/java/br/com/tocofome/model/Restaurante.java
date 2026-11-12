package br.com.tocofome.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurante extends Usuario {
    private List<Produto> cardapio = new ArrayList<>();

    public Restaurante(String nome, String email) {
        super(nome, email);
    }

    public void adicionarProduto(Produto produto) {
        cardapio.add(produto);
    }

    public boolean temProduto(Produto produto) {
        return cardapio.contains(produto);
    }

    public void iniciarPreparacao(Pedido pedido) {
        pedido.mudarStatus("Em preparação");
    }

    public void marcarComoPronto(Pedido pedido) {
        pedido.mudarStatus("Pronto para entrega");
    }

    @Override
    public String getTipo() {
        return "Restaurante";
    }
}
