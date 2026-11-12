package br.com.tocofome.app;

import br.com.tocofome.interfaces.ConsultavelStatus;
import br.com.tocofome.model.Cliente;
import br.com.tocofome.model.Entregador;
import br.com.tocofome.model.Entrega;
import br.com.tocofome.model.Pedido;
import br.com.tocofome.model.Produto;
import br.com.tocofome.model.Restaurante;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    // Dados de exemplo para a demonstração do sistema.
    private static Cliente cliente = new Cliente(
            "Ana", "ana@email.com", "Rua das Flores, 25");
    private static Restaurante restaurante = new Restaurante(
            "Toco Burgers", "contato@tocoburgers.com");
    private static Entregador entregador = new Entregador(
            "Carlos", "carlos@email.com");

    private static Produto[] produtos = {
        new Produto("X-Burguer", 18.90),
        new Produto("Batata frita", 9.50),
        new Produto("Suco natural", 6.50)
    };

    private static Pedido pedido;
    private static Entrega entrega;

    public static void main(String[] args) {
        cadastrarProdutos();

        int opcao;
        do {
            exibirMenu();
            opcao = lerNumero("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    exibirCardapio();
                    break;
                case 2:
                    realizarPedido();
                    break;
                case 3:
                    acompanharPedido();
                    break;
                case 4:
                    atualizarAndamento();
                    break;
                case 0:
                    System.out.println("Obrigado por utilizar o TocoFome!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    private static void cadastrarProdutos() {
        for (Produto produto : produtos) {
            restaurante.adicionarProduto(produto);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n========== TOCOFOME ==========");
        System.out.println("1 - Consultar cardápio");
        System.out.println("2 - Realizar pedido");
        System.out.println("3 - Acompanhar pedido");
        System.out.println("4 - Atualizar andamento do pedido");
        System.out.println("0 - Sair");
        System.out.println("==============================");
    }

    private static void exibirCardapio() {
        System.out.println("\n--- CARDÁPIO: " + restaurante.getNome() + " ---");
        for (int i = 0; i < produtos.length; i++) {
            System.out.printf("%d - %s | R$ %.2f%n",
                    i + 1, produtos[i].getNome(), produtos[i].getPreco());
        }
    }

    private static void realizarPedido() {
        if (pedido != null && !pedido.getStatus().equals("Entregue")) {
            System.out.println("Já existe um pedido em andamento.");
            return;
        }

        pedido = new Pedido(cliente, restaurante);
        entrega = null;
        boolean adicionouProduto = false;

        System.out.println("\nOlá, " + cliente.getNome() + "! Vamos montar seu pedido.");

        int opcaoProduto;
        do {
            exibirCardapio();
            System.out.println("0 - Finalizar escolha dos produtos");
            opcaoProduto = lerNumero("Escolha o produto: ");

            if (opcaoProduto >= 1 && opcaoProduto <= produtos.length) {
                int quantidade = lerNumero("Quantidade: ");

                if (quantidade > 0) {
                    pedido.adicionarItem(produtos[opcaoProduto - 1], quantidade);
                    adicionouProduto = true;
                    System.out.println("Produto adicionado ao pedido.");
                } else {
                    System.out.println("Informe uma quantidade maior que zero.");
                }
            } else if (opcaoProduto != 0) {
                System.out.println("Produto inválido. Escolha uma opção do cardápio.");
            }
        } while (opcaoProduto != 0);

        if (adicionouProduto) {
            pedido.confirmar();
            System.out.println("\nPedido registrado!");
            System.out.println(pedido.resumo());
            System.out.println(pedido.consultarStatus());
        } else {
            pedido = null;
            System.out.println("Pedido cancelado: nenhum produto foi escolhido.");
        }
    }

    private static void acompanharPedido() {
        if (pedido == null) {
            System.out.println("Nenhum pedido foi realizado ainda.");
            return;
        }

        System.out.println("\n--- ACOMPANHAMENTO ---");
        System.out.println(pedido.resumo());
        mostrarStatus(pedido);

        if (entrega != null) {
            mostrarStatus(entrega);
        }
    }

    private static void atualizarAndamento() {
        if (pedido == null) {
            System.out.println("Realize um pedido antes de atualizar seu andamento.");
            return;
        }

        String status = pedido.getStatus();

        if (status.equals("Aguardando confirmação")) {
            restaurante.iniciarPreparacao(pedido);
            System.out.println("O restaurante iniciou a preparação do pedido.");
        } else if (status.equals("Em preparação")) {
            restaurante.marcarComoPronto(pedido);
            System.out.println("O pedido está pronto para entrega.");
        } else if (status.equals("Pronto para entrega")) {
            entrega = new Entrega(pedido);
            entregador.assumirEntrega(entrega);
            System.out.println("O entregador assumiu o pedido.");
        } else if (status.equals("Em entrega")) {
            entregador.concluirEntrega(entrega);
            System.out.println("O pedido foi entregue ao cliente.");
        } else if (status.equals("Entregue")) {
            System.out.println("Este pedido já foi concluído.");
        } else {
            System.out.println("O pedido ainda não foi confirmado.");
        }

        acompanharPedido();
    }

    // O mesmo método recebe Pedido ou Entrega por meio da interface.
    private static void mostrarStatus(ConsultavelStatus item) {
        System.out.println(item.consultarStatus());
    }

    private static int lerNumero(String mensagem) {
        System.out.print(mensagem);
        String resposta = entrada.nextLine();

        try {
            return Integer.parseInt(resposta);
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
