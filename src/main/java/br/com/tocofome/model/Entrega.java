package br.com.tocofome.model;

import br.com.tocofome.interfaces.ConsultavelStatus;

public class Entrega implements ConsultavelStatus {
    private Pedido pedido;
    private String status = "Aguardando entregador";
    private Entregador entregador;

    public Entrega(Pedido pedido) {
        this.pedido = pedido;
    }

    public void iniciar(Entregador entregador) {
        this.entregador = entregador;
        status = "Em entrega";
        pedido.mudarStatus("Em entrega");
    }

    public void finalizar() {
        status = "Entrega concluída";
        pedido.mudarStatus("Entregue");
    }

    @Override
    public String consultarStatus() {
        return "Status da entrega: " + status;
    }
}
