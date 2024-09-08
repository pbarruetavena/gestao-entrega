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
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ListarPedidoController {

    private App app;

    @FXML
    private ListView<String> pedidoListView;

    @FXML
    private Button voltarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Label clienteLabel;

    @FXML
    private Label valorTotalLabel;

    @FXML
    private TextField valorTotalText;

    @FXML
    private ListView<ItemPed> itensListView;

    private PedidoController pedidoController;
    private List<Pedido> pedidosList;
    private Pedido pedidoSelecionado;
    private ObservableList<ItemPed> listaObsItens;

   

    private void adicionarListenersParaTextFields() {
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> mostrarBotaoSalvar();
        valorTotalText.textProperty().addListener(textChangeListener);
    }

    private void mostrarBotaoSalvar() {
        salvarButton.setVisible(true);
    }

    private void carregarPedidos() {
        pedidosList = pedidoController.listar();
        if (pedidosList == null || pedidosList.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            ObservableList<String> pedidosDescricoes = FXCollections.observableArrayList();
            for (Pedido pedido : pedidosList) {
                pedidosDescricoes.add("Pedido " + pedido.getId() + " - " + pedido.getCliente().getNome());
            }
            pedidoListView.setItems(pedidosDescricoes);
            pedidoListView.setOnMouseClicked((MouseEvent event) -> {
                int selectedIndex = pedidoListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    mostrarDetalhesPedido(selectedIndex);
                }
            });
        }
    }

    private void mostrarDetalhesPedido(int index) {
        if (index >= 0 && index < pedidosList.size()) {
            pedidoSelecionado = pedidosList.get(index);
            valorTotalText.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
            atualizarItensListView(pedidoSelecionado.getItens());
            mostrarCampos(true);
        }
    }

    private void atualizarItensListView(List<ItemPed> itens) {
        listaObsItens = FXCollections.observableArrayList(itens);
        itensListView.setItems(listaObsItens);
    }

    private void mostrarCampos(boolean visivel) {
        valorTotalText.setVisible(visivel);
        itensListView.setVisible(visivel);
        salvarButton.setVisible(visivel);
        deletarButton.setVisible(visivel);
    }

    @FXML
    private void salvarAtualizacoes(ActionEvent event) throws IOException {
        if (pedidoSelecionado != null) {
            try {
                Double valorTotal = Double.parseDouble(valorTotalText.getText());
                pedidoSelecionado.setValorTotal(valorTotal);
                pedidoController.atualizar(pedidoSelecionado);
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Pedido atualizado com sucesso!");
                salvarButton.setVisible(false);
            } catch (NumberFormatException e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Valor total inválido.");
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao atualizar o pedido: " + e.getMessage());
            }
        }
    }

    @FXML
    private void deletarPedido(ActionEvent event) {
        if (pedidoSelecionado != null) {
            try {
                pedidoController.remover(pedidoSelecionado.getId());
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Pedido removido com sucesso!");
                carregarPedidos();
                valorTotalText.clear();
                salvarButton.setVisible(false);
                deletarButton.setVisible(false);
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao remover o pedido: " + e.getMessage());
            }
        }
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
