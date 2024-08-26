package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ListarPedidoController implements Initializable {

    @FXML
    private ListView<Pedido> pedidoListView;

    @FXML
    private TextArea detalhesPedidoText;

    @FXML
    private Button voltarButton;

    private PedidoController pedidoController;
    private ObservableList<Pedido> pedidos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoController = new PedidoController(); 
        pedidos = FXCollections.observableArrayList(pedidoController.listar()); 

        pedidoListView.setItems(pedidos);
        pedidoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarDetalhesPedido(newValue);
            }
        });

        voltarButton.setOnAction(event -> voltarTela());
    }

    private void mostrarDetalhesPedido(Pedido pedido) {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Status: ").append(pedido.getStatus()).append("\n");
        detalhes.append("Valor Total: R$ ").append(String.format("%.2f", pedido.getValorTotal())).append("\n");

    }

    private void voltarTela() {
       
    }
}
