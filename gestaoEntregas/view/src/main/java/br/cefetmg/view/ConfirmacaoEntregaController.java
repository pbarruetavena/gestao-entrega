package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.Pedido;
import br.cefetmg.entidades.StatusPedido;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ConfirmacaoEntregaController implements Initializable {

    @FXML
    private Button confirmarEntrega;

    private PedidoController pedidoController;
    private App app;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoController = new PedidoController();
    }

    @FXML
    private void confirmarEntrega(ActionEvent event) {
        Pedido pedidoSelecionado = GlobalContext.getCurrentPedido();
        if (pedidoSelecionado != null) {
            pedidoSelecionado.setStatus(StatusPedido.ENTREGUE);
            pedidoController.atualizar(pedidoSelecionado);
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Entrega confirmada!");
            try {
                app.setRoot("InicialEntrega");  
            } catch (Exception e) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar à tela inicial.");
            }
        }
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        Pedido pedidoSelecionado = GlobalContext.getCurrentPedido();
        if (pedidoSelecionado != null) {
            pedidoSelecionado.setStatus(StatusPedido.EM_PREPARO);
        }
        app.setRoot("InicialEntrega");
    }

    public void setApp(App app) {
        this.app = app;
    }
}
