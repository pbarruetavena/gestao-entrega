package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.Pedido;
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
    private Pedido pedidoSelecionado;
    private App app;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoController = new PedidoController();
    }

    @FXML
    private void confirmarEntrega(ActionEvent event) {
        if (pedidoSelecionado != null) {
            //pedidoSelecionado.setStatus.('');
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

    public void setApp(App app) {
        this.app = app;
    }

    public void setPedidoSelecionado(Pedido pedido) {
        this.pedidoSelecionado = pedido;
    }
     @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    }

}
