package br.cefetmg.view;

import br.cefetmg.controller.ClienteController;
import br.cefetmg.entidades.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.value.ChangeListener;

public class ListarClientesController {

    @FXML
    private ListView<String> clienteListView;

    @FXML
    private Button voltarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Label BairroLabel;

    @FXML
    private TextField BairroText;

    @FXML
    private Label LograLabel;

    @FXML
    private TextField LograText;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private Button Salvar;

    @FXML
    private Label TelLabel;

    @FXML
    private TextField TelText;

    @FXML
    private Label Titulo;

    @FXML
    private Label cpfLabel;

    @FXML
    private TextField cpfText;

    private ClienteController clienteController;
    private List<Cliente> clientesList;
    private Cliente clienteSelecionado;

    private String nomeOriginal;
    private String enderecoOriginal;
    private String bairroOriginal;
    private String telefoneOriginal;
    private String cpfOriginal;

    public void initialize() {
        clienteController = new ClienteController();
        carregarClientes();
        adicionarListenersParaTextFields();
    }

    private void adicionarListenersParaTextFields() {
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> mostrarBotaoSalvar();

        NomeText.textProperty().addListener(textChangeListener);
        LograText.textProperty().addListener(textChangeListener);
        BairroText.textProperty().addListener(textChangeListener);
        TelText.textProperty().addListener(textChangeListener);
        cpfText.textProperty().addListener(textChangeListener);
    }

    private void mostrarBotaoSalvar() {
        if (clienteSelecionado != null) {
            boolean modificou = !NomeText.getText().equals(nomeOriginal)
                    || !LograText.getText().equals(enderecoOriginal)
                    || !BairroText.getText().equals(bairroOriginal)
                    || !TelText.getText().equals(telefoneOriginal)
                    || !cpfText.getText().equals(cpfOriginal);
            salvarButton.setVisible(modificou);
        }
    }

    private void carregarClientes() {
        clientesList = clienteController.listar();
        if (clientesList == null || clientesList.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            ObservableList<String> clienteNomes = FXCollections.observableArrayList();
            for (Cliente cliente : clientesList) {
                clienteNomes.add(cliente.getNome());
            }
            clienteListView.setItems(clienteNomes);
            clienteListView.setOnMouseClicked((MouseEvent event) -> {
                mostrarDetalhesCliente(clienteListView.getSelectionModel().getSelectedIndex());
            });
        }
    }

    private void mostrarDetalhesCliente(int index) {
        if (index >= 0 && index < clientesList.size()) {
            clienteSelecionado = clientesList.get(index);

            nomeOriginal = clienteSelecionado.getNome();
            enderecoOriginal = clienteSelecionado.getEndereco();
            bairroOriginal = clienteSelecionado.getBairro();
            telefoneOriginal = clienteSelecionado.getTelefone();
            cpfOriginal = clienteSelecionado.getCpf();

            NomeText.setText(nomeOriginal);
            LograText.setText(enderecoOriginal);
            BairroText.setText(bairroOriginal);
            TelText.setText(telefoneOriginal);
            cpfText.setText(cpfOriginal);

            mostrarCampos(true);
        }
    }

    private void mostrarCampos(boolean visivel) {
        NomeText.setVisible(visivel);
        LograText.setVisible(visivel);
        BairroText.setVisible(visivel);
        TelText.setVisible(visivel);
        cpfText.setVisible(visivel);
        salvarButton.setVisible(visivel && !NomeText.getText().equals(nomeOriginal)
                || !LograText.getText().equals(enderecoOriginal)
                || !BairroText.getText().equals(bairroOriginal)
                || !TelText.getText().equals(telefoneOriginal)
                || !cpfText.getText().equals(cpfOriginal));
        deletarButton.setVisible(visivel);
    }

    @FXML
    private void salvarAtualizacoes(ActionEvent event) {
        if (clienteSelecionado != null) {
            clienteSelecionado.setNome(NomeText.getText());
            clienteSelecionado.setEndereco(LograText.getText());
            clienteSelecionado.setBairro(BairroText.getText());
            clienteSelecionado.setTelefone(TelText.getText());
            clienteSelecionado.setCpf(cpfText.getText());

            try {
                clienteController.atualizar(clienteSelecionado);
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Cliente atualizado com sucesso!");
                salvarButton.setVisible(false);
                // Atualiza os valores originais após a alteração
                nomeOriginal = clienteSelecionado.getNome();
                enderecoOriginal = clienteSelecionado.getEndereco();
                bairroOriginal = clienteSelecionado.getBairro();
                telefoneOriginal = clienteSelecionado.getTelefone();
                cpfOriginal = clienteSelecionado.getCpf();
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao atualizar o cliente: " + e.getMessage());
            }
        }
    }

    @FXML
    private void deletarCliente(ActionEvent event) {
        if (clienteSelecionado != null) {
            try {
                clienteController.remover(clienteSelecionado.getId());
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Cliente removido com sucesso!");
                carregarClientes();
                NomeText.clear();
                LograText.clear();
                BairroText.clear();
                TelText.clear();
                cpfText.clear();
                salvarButton.setVisible(false);
                deletarButton.setVisible(false);
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao remover o cliente: " + e.getMessage());
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

    private App app;
}

