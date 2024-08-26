package br.cefetmg.view;

import br.cefetmg.entidades.Produto;
import br.cefetmg.controller.ProdutoController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ListarProdutoController implements Initializable {

    @FXML
    private ListView<Produto> produtoListView;

    @FXML
    private TextArea detalhesProdutoText;

    @FXML
    private Button voltarButton;

    private ProdutoController produtoController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoController = new ProdutoController();
        ObservableList<Produto> produtos = FXCollections.observableArrayList(produtoController.listar());
        produtoListView.setItems(produtos);

        produtoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarDetalhesProduto(newValue);
            }
        });
    }

    private void mostrarDetalhesProduto(Produto produto) {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Nome: ").append(produto.getNome()).append("\n");
        detalhes.append("Localização: ").append(produto.getLocalizacao()).append("\n");

        detalhesProdutoText.setText(detalhes.toString());
    }

    @FXML
    private void voltarTela() {
    }
}
