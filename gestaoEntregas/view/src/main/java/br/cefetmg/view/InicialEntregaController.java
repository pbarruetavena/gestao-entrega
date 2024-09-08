package br.cefetmg.view;

import br.cefetmg.controller.PedidoController;
import br.cefetmg.entidades.ItemPed;
import br.cefetmg.entidades.Pedido;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

        // Configurando o listener para a lista de pedidos
        pedidoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = pedidoListView.getSelectionModel().getSelectedIndex();
                mostrarDetalhesPedido(selectedIndex);
            }
        });
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
        }
    }

    private void mostrarDetalhesPedido(int index) {
        if (index >= 0 && index < pedidosList.size()) {
            pedidoSelecionado = pedidosList.get(index);
            valorTotalText.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
            atualizarItensListView(pedidoSelecionado.getItens());
            mostrarCampos(true);

            iniciarEntregaButton.setVisible(true);  // Mostrar botão de iniciar entrega
        }
    }

    @FXML
    private void iniciarEntrega(ActionEvent event) throws IOException {
        if (pedidoSelecionado != null) {
           // pedidoSelecionado.setStatus("Em entrega");  // Alterar o status do pedido
            pedidoController.atualizar(pedidoSelecionado);  // Atualizar no banco de dados
            exibirAlerta(Alert.AlertType.INFORMATION, "Entrega Iniciada", "O status do pedido foi alterado para 'Em entrega'.");
            app.setRoot("ConfirmacaoEntrega");  // Navegar para a próxima tela
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
        app.setRoot("MenuInicial");  // Navegar de volta ao menu inicial
    }

    public void setApp(App app) {
        this.app = app;
    }
}
