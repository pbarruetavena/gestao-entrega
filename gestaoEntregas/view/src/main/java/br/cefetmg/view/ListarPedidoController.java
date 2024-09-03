package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.ItemPed;
import br.cefetmg.entidades.Pedido;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class ListarPedidoController {
    
    private App app;

    @FXML
    private ListView<Pedido> pedidoListView;

    @FXML
    private TextArea detalhesPedidoText;

    @FXML
    private Button voltarButton;

    private PedidoController pedidoController;
    private ObservableList<Pedido> pedidos;

    private void  setApp (App app){
        this.app  = app;
    }
    
    public void initialize() {
        pedidoController = new PedidoController(); 
        pedidos = FXCollections.observableArrayList(pedidoController.listar()); 
        pedidoListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Pedido>() {
                @Override
                public String toString(Pedido i) {
                    return i.getData().toString() + " - R$" + i.getValorTotal();
                }

                @Override
                public Pedido fromString(String string) {
                    return null;
                }
            }));
        pedidoListView.setItems(pedidos);
        pedidoListView.setOnMouseClicked((MouseEvent e) -> {
                this.mostrarDetalhesPedido(pedidoListView.getSelectionModel().getSelectedItem());
            });
//        pedidoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {;
//            if (newValue != null) {
//                mostrarDetalhesPedido(newValue);
//            }
//        });

    }

    private void mostrarDetalhesPedido(Pedido pedido) {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Status: ").append(pedido.getStatus()).append("\n");
        detalhes.append("Valor Total: R$ ").append(String.format("%.2f", pedido.getValorTotal())).append("\n");
        detalhes.append("Itens: \n");
        for(ItemPed i : pedido.getItens()) {
            detalhes.append("   Produto: ").append(i.getProduto().getNome()).append("\n");
            detalhes.append("   Preço: ").append(i.getQuantidade()).append(" x R$").append(i.getValorUnitario()).append("\n");
        }
        detalhesPedidoText.setText(detalhes.toString());

    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    
    }
}
