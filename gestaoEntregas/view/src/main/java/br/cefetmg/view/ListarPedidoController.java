package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.ItemPed;
import br.cefetmg.entidades.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

public class ListarPedidoController implements Initializable {

    private App app;

    @FXML
    private ListView<String> pedidoListView;

    @FXML
    private Button voltarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Label clienteLabel;

    @FXML
    private Label valorTotalLabel;

    @FXML
    private TextField valorTotalText;

    @FXML
    private TextField statusText;

    @FXML
    private ListView<String> itensListView;

    private PedidoController pedidoController;
    private List<Pedido> pedidosList;
    private Pedido pedidoSelecionado;
    private ObservableList<String> listaObsItens;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoController = new PedidoController();
        carregarPedidos();

        pedidoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = pedidoListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    mostrarDetalhesPedido(selectedIndex);
                }
            }
        });

        adicionarListenersParaTextFields();
    }

    private void adicionarListenersParaTextFields() {
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> {
        };
        valorTotalText.textProperty().addListener(textChangeListener);
        statusText.textProperty().addListener(textChangeListener);
    }

    private void carregarPedidos() {
        try {
            pedidosList = pedidoController.listar();
            if (pedidosList == null || pedidosList.isEmpty()) {
                pedidoListView.setItems(FXCollections.observableArrayList("Nenhum pedido encontrado."));
            } else {
                ObservableList<String> pedidosDescricoes = FXCollections.observableArrayList();
                for (Pedido pedido : pedidosList) {
                    pedidosDescricoes.add("Pedido " + pedido.getId() + " - " + pedido.getCliente().getNome());
                }
                pedidoListView.setItems(pedidosDescricoes);
            }
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao carregar os pedidos: " + e.getMessage());
        }
    }

    private void mostrarDetalhesPedido(int index) {
        if (index >= 0 && index < pedidosList.size()) {
            pedidoSelecionado = pedidosList.get(index);

            valorTotalText.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
            statusText.setText(String.valueOf(pedidoSelecionado.getStatus()));

            List<String> itensDescricoes = new ArrayList<>();
            for (ItemPed item : pedidoSelecionado.getItens()) {
                itensDescricoes.add(item.getProduto().getNome() + " - Qtd: " + item.getQuantidade());
            }
            atualizarItensListView(itensDescricoes);
            mostrarCampos(true);
        }
    }

    private void atualizarItensListView(List<String> itens) {
        listaObsItens = FXCollections.observableArrayList(itens);
        itensListView.setItems(listaObsItens);
    }

    private void mostrarCampos(boolean visivel) {
        valorTotalText.setVisible(visivel);
        statusText.setVisible(visivel);
        itensListView.setVisible(visivel);
        deletarButton.setVisible(visivel);
    }

    @FXML
    private void deletarPedido(ActionEvent event) {
        if (pedidoSelecionado != null) {
            try {
                pedidoController.remover(pedidoSelecionado.getId());
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Pedido removido com sucesso!");
                carregarPedidos();
                limparCampos();
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao remover o pedido: " + e.getMessage());
            }
        }
    }

    private void limparCampos() {
        valorTotalText.clear();
        statusText.clear();
        itensListView.setItems(FXCollections.observableArrayList());
        deletarButton.setVisible(false);
    }

    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    }

    public void setApp(App app) {
        this.app = app;
    }
}
