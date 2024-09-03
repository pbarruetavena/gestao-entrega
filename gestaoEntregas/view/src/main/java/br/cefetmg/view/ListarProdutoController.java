package br.cefetmg.view;

import br.cefetmg.controller.ProdutoController;
import br.cefetmg.entidades.Produto;
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

public class ListarProdutoController {

    @FXML
    private ListView<String> produtoListView;

    @FXML
    private Button voltarButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button deletarButton;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private Label LocalizacaoLabel;

    @FXML
    private TextField LocalizacaoText;

    private ProdutoController produtoController;
    private List<Produto> produtoList;
    private Produto produtoSelecionado;

    private String nomeOriginal;
    private String localizacaoOriginal;

    public void initialize() {
        produtoController = new ProdutoController();
        carregarProdutos();
        adicionarListenersParaTextFields();
    }

    private void adicionarListenersParaTextFields() {
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> mostrarBotaoSalvar();

        NomeText.textProperty().addListener(textChangeListener);
        LocalizacaoText.textProperty().addListener(textChangeListener);
    }

    private void mostrarBotaoSalvar() {
        if (produtoSelecionado != null) {
            String nomeAtual = NomeText.getText();
            String localizacaoAtual = LocalizacaoText.getText();
            salvarButton.setVisible(!nomeAtual.equals(nomeOriginal) || !localizacaoAtual.equals(localizacaoOriginal));
        }
    }

    private void carregarProdutos() {
        produtoList = produtoController.listar();

        if (produtoList == null || produtoList.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            ObservableList<String> produtosNome = FXCollections.observableArrayList();
            for (Produto produto : produtoList) {
                produtosNome.add(produto.getNome());
            }
            produtoListView.setItems(produtosNome);
            produtoListView.setOnMouseClicked((MouseEvent event) -> {
                mostrarDetalhesProduto(produtoListView.getSelectionModel().getSelectedIndex());
            });
        }
    }

    private void mostrarDetalhesProduto(int index) {
        if (index >= 0 && index < produtoList.size()) {
            produtoSelecionado = produtoList.get(index);

            nomeOriginal = produtoSelecionado.getNome();
            localizacaoOriginal = produtoSelecionado.getLocalizacao();

            NomeText.setText(nomeOriginal);
            LocalizacaoText.setText(localizacaoOriginal);

            mostrarCampos(true);
        }
    }

    private void mostrarCampos(boolean visivel) {
        NomeText.setVisible(visivel);
        LocalizacaoText.setVisible(visivel);
        deletarButton.setVisible(visivel);
    }

    @FXML
    private void salvarAtualizacoes(ActionEvent event) {
        if (produtoSelecionado != null) {
            produtoSelecionado.setNome(NomeText.getText());
            produtoSelecionado.setLocalizacao(LocalizacaoText.getText());

            try {
                produtoController.atualizar(produtoSelecionado);
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Produto atualizado com sucesso!");
                salvarButton.setVisible(false); 
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao atualizar o produto: " + e.getMessage());
            }
        }
    }

    @FXML
    private void deletarProduto(ActionEvent event) {
        if (produtoSelecionado != null) {
            try {
                produtoController.remover(produtoSelecionado.getId());
                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Produto removido com sucesso!");
                carregarProdutos();
                NomeText.clear();
                LocalizacaoText.clear();
                salvarButton.setVisible(false);
                deletarButton.setVisible(false);
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao remover o produto: " + e.getMessage());
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
