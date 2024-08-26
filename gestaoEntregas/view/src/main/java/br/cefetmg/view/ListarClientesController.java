package br.cefetmg.view;

import br.cefetmg.controller.ClienteController;
import br.cefetmg.entidades.Cliente;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class ListarClientesController {

    @FXML
    private ListView<String> clienteListView;

    @FXML
    private TextArea detalhesClienteText;

    @FXML
    private Button voltarButton;

    private ClienteController clienteController;
    private List<Cliente> clientesList;

    public void initialize() {
        clienteController = new ClienteController();
    }

    private void carregarClientes() {
        clientesList = clienteController.listar();
        ObservableList<String> clienteNomes = FXCollections.observableArrayList();

        for (Cliente cliente : clientesList) {
            clienteNomes.add(cliente.getNome());
        }

        clienteListView.setItems(clienteNomes);

        clienteListView.setOnMouseClicked((MouseEvent event) -> {
            mostrarDetalhesCliente(clienteListView.getSelectionModel().getSelectedIndex());
        });
    }

    private void mostrarDetalhesCliente(int index) {
        if (index >= 0 && index < clientesList.size()) {
            Cliente clienteSelecionado = clientesList.get(index);
            StringBuilder detalhes = new StringBuilder();

            detalhes.append("Nome: ").append(clienteSelecionado.getNome()).append("\n");
            detalhes.append("Endereço: ").append(clienteSelecionado.getEndereco()).append("\n");
            detalhes.append("Bairro: ").append(clienteSelecionado.getBairro()).append("\n");
            detalhes.append("Telefone: ").append(clienteSelecionado.getTelefone()).append("\n");
            detalhes.append("CPF: ").append(clienteSelecionado.getCpf()).append("\n");

            detalhesClienteText.setText(detalhes.toString());
        }
    }
    private App app;

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("TelaFuncionario");
    
}
}