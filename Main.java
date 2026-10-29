package br.com.tocofome.app;

import br.com.tocofome.interfaces.ConsultavelStatus;
import br.com.tocofome.model.Cliente;
import br.com.tocofome.model.Entregador;
import br.com.tocofome.model.Entrega;
import br.com.tocofome.model.Pedido;
import br.com.tocofome.model.Produto;
import br.com.tocofome.model.Restaurante;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Ana", "ana@email.com", "Rua das Flores, 25");
        Restaurante restaurante = new Restaurante("Toco Burgers", "contato@tocoburgers.com");
        Entregador entregador = new Entregador("Carlos", "carlos@email.com");

        Produto hamburguer = new Produto("X-Burguer", 18.90);
        Produto suco = new Produto("Suco natural", 6.50);
        restaurante.adicionarProduto(hamburguer);
        restaurante.adicionarProduto(suco);

        Pedido pedido = new Pedido(cliente, restaurante);
        pedido.adicionarItem(hamburguer, 2);
        pedido.adicionarItem(suco, 1);
        pedido.confirmar();

        System.out.println("=== TOCOFOME ===");
        System.out.println(pedido.resumo());
        System.out.println(pedido.consultarStatus());

        restaurante.iniciarPreparacao(pedido);
        System.out.println("\nRestaurante começou a preparar.");
        System.out.println(pedido.consultarStatus());

        restaurante.marcarComoPronto(pedido);
        Entrega entrega = new Entrega(pedido);
        entregador.assumirEntrega(entrega);

        System.out.println("\nEntregador assumiu o pedido.");
        List<ConsultavelStatus> acompanhamentos = Arrays.asList(pedido, entrega);
        for (ConsultavelStatus item : acompanhamentos) {
            System.out.println(item.consultarStatus());
        }

        entregador.concluirEntrega(entrega);
        System.out.println("\nEntrega finalizada.");
        for (ConsultavelStatus item : acompanhamentos) {
            System.out.println(item.consultarStatus());
        }
    }
}
