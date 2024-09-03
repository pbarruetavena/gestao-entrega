package br.cefetmg.view;

import br.cefetmg.controller.FuncionarioController;
import br.cefetmg.entidades.Funcionario;
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

public class ListarFuncionariosController {

    @FXML
    private ListView<String> funcionarioListView;

    @FXML
    private Button voltarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Label nomeLabel;

    @FXML
    private TextField nomeText;

    @FXML
    private Label telefoneLabel;

    @FXML
    private TextField telefoneText;

    @FXML
    private Label tituloLabel;

    private FuncionarioController funcionarioController;
    private List<Funcionario> funcionariosList;
    private Funcionario funcionarioSelecionado;

    private String nomeOriginal;
    private String telefoneOriginal;

    private App app;

    @FXML
    public void initialize() {
        funcionarioController = new FuncionarioController();
        carregarFuncionarios();
        adicionarListenersParaTextFields();
        salvarButton.setVisible(false);
        deletarButton.setVisible(false);
    }

    private void adicionarListenersParaTextFields() {
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> mostrarBotaoSalvar();

        nomeText.textProperty().addListener(textChangeListener);
        telefoneText.textProperty().addListener(textChangeListener);
    }

    private void mostrarBotaoSalvar() {
        if (funcionarioSelecionado != null) {
            String nomeAtual = nomeText.getText();
            String telefoneAtual = telefoneText.getText();
            salvarButton.setVisible(!nomeAtual.equals(nomeOriginal) || !telefoneAtual.equals(telefoneOriginal));
        }
    }

    private void carregarFuncionarios() {
        funcionariosList = funcionarioController.listar();
        if (funcionariosList == null || funcionariosList.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            ObservableList<String> funcionarioNomes = FXCollections.observableArrayList();
            for (Funcionario funcionario : funcionariosList) {
                funcionarioNomes.add(funcionario.getNome());
            }
            funcionarioListView.setItems(funcionarioNomes);
            funcionarioListView.setOnMouseClicked((MouseEvent event) -> {
                int selectedIndex = funcionarioListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    mostrarDetalhesFuncionario(selectedIndex);
                }
            });
        }
    }

    private void mostrarDetalhesFuncionario(int index) {
        if (index >= 0 && index < funcionariosList.size()) {
            funcionarioSelecionado = funcionariosList.get(index);

            nomeOriginal = funcionarioSelecionado.getNome();
            telefoneOriginal = funcionarioSelecionado.getTelefone();

            nomeText.setText(nomeOriginal);
            telefoneText.setText(telefoneOriginal);

            mostrarCampos(true);
        }
    }

    private void mostrarCampos(boolean visivel) {
        nomeText.setVisible(visivel);
        telefoneText.setVisible(visivel);
        deletarButton.setVisible(visivel); 
    }

    @FXML
    private void salvarAtualizacoes(ActionEvent event) {
        if (funcionarioSelecionado != null) {
            funcionarioSelecionado.setNome(nomeText.getText());
            funcionarioSelecionado.setTelefone(telefoneText.getText());

            try {
                funcionarioController.atualizar(funcionarioSelecionado);
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
                salvarButton.setVisible(false); 
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao atualizar o funcionário: " + e.getMessage());
            }
        }
    }

    @FXML
    private void deletarFuncionario(ActionEvent event) {
        if (funcionarioSelecionado != null) {
            try {
                funcionarioController.remover(funcionarioSelecionado.getId());
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário removido com sucesso!");
                carregarFuncionarios();
                nomeText.clear();
                telefoneText.clear();
                salvarButton.setVisible(false);
                deletarButton.setVisible(false);
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao remover o funcionário: " + e.getMessage());
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
