package br.com.tocofome.model;

import br.com.tocofome.interfaces.ConsultavelStatus;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements ConsultavelStatus {
    private Cliente cliente;
    private Restaurante restaurante;
    private List<ItemPedido> itens = new ArrayList<>();
    private String status = "Em montagem";

    public Pedido(Cliente cliente, Restaurante restaurante) {
        this.cliente = cliente;
        this.restaurante = restaurante;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (restaurante.temProduto(produto)) {
            itens.add(new ItemPedido(produto, quantidade));
        } else {
            System.out.println("Esse produto não está no cardápio do restaurante.");
        }
    }

    public void confirmar() {
        if (!itens.isEmpty()) {
            status = "Aguardando confirmação";
        } else {
            System.out.println("O pedido precisa ter pelo menos um produto.");
        }
    }

    public void mudarStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public String resumo() {
        return "Cliente: " + cliente.getNome()
                + " | Restaurante: " + restaurante.getNome()
                + " | Total: R$ " + String.format("%.2f", calcularTotal());
    }

    @Override
    public String consultarStatus() {
        return "Status do pedido: " + status;
    }
}
