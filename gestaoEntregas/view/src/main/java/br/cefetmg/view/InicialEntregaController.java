package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.ItemPed;
import br.cefetmg.entidades.Pedido;
import br.cefetmg.entidades.StatusPedido;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class InicialEntregaController implements Initializable {

    private App app;

    @FXML
    private ListView<String> pedidoListView;

    @FXML
    private Button voltarButton;
    @FXML
    private Button iniciarEntregaButton;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoController = new PedidoController();
        carregarPedidos();

        iniciarEntregaButton.setVisible(false);

        pedidoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = pedidoListView.getSelectionModel().getSelectedIndex();
                mostrarDetalhesPedido(selectedIndex);
            }
        });

        // Configurar a ListView de itens para exibir ItemPed corretamente
        itensListView.setCellFactory(new Callback<ListView<ItemPed>, ListCell<ItemPed>>() {
            @Override
            public ListCell<ItemPed> call(ListView<ItemPed> param) {
                return new ListCell<ItemPed>() {
                    @Override
                    protected void updateItem(ItemPed item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getProduto().getNome() + " - Qtd: " + item.getQuantidade());
                        }
                    }
                };
            }
        });
    }

  private void carregarPedidos() {
    try {
        pedidosList = pedidoController.listar();
        if (pedidosList == null || pedidosList.isEmpty()) {
            pedidoListView.setItems(FXCollections.observableArrayList("Nenhum pedido encontrado."));
        } else {
            ObservableList<String> pedidosDescricoes = FXCollections.observableArrayList();
            // Filtra apenas os pedidos que não foram entregues
            for (Pedido pedido : pedidosList) {
                if (pedido.getStatus() != StatusPedido.ENTREGUE) {
                    pedidosDescricoes.add("Pedido " + pedido.getId() + " - " + pedido.getCliente().getNome());
                }
            }
            if (pedidosDescricoes.isEmpty()) {
                pedidoListView.setItems(FXCollections.observableArrayList("Nenhum pedido pendente de entrega."));
            } else {
                pedidoListView.setItems(pedidosDescricoes);
            }
        }
    } catch (Exception e) {
        exibirAlerta(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao carregar os pedidos: " + e.getMessage());
    }
}


    private void mostrarDetalhesPedido(int index) {
        if (index >= 0 && index < pedidosList.size()) {
            pedidoSelecionado = pedidosList.get(index);

            valorTotalText.setText(String.valueOf(pedidoSelecionado.getValorTotal()));

            List<ItemPed> itens = pedidoSelecionado.getItens();
            atualizarItensListView(itens);

            mostrarCampos(true);
            iniciarEntregaButton.setVisible(true);
        }
    }

    @FXML
    private void iniciarEntrega(ActionEvent event) throws IOException {
        if (pedidoSelecionado != null) {
            pedidoSelecionado.setStatus(StatusPedido.EM_ENTREGA);
            pedidoController.atualizar(pedidoSelecionado);
            if (pedidoSelecionado != null) {
                GlobalContext.setCurrentPedido(pedidoSelecionado);
                app.setRoot("ConfirmacaoEntrega");
            }
        }
    }

    private void atualizarItensListView(List<ItemPed> itens) {
        listaObsItens = FXCollections.observableArrayList(itens);
        itensListView.setItems(listaObsItens);
    }

    private void mostrarCampos(boolean visivel) {
        valorTotalText.setVisible(visivel);
        itensListView.setVisible(visivel);
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
        app.setRoot("MenuInicial");
    }

    public void setApp(App app) {
        this.app = app;
    }
}
