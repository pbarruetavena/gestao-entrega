package br.cefetmg.view;

import br.cefetmg.controller.ProdutoController;
import br.cefetmg.entidades.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarProdutoController {

    @FXML
    private Label LocLabel;

    @FXML
    private TextField LocText;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private Button Salvar;

    @FXML
    private Label TituloProduto;

    private ProdutoController ProdutoController = new ProdutoController();
    private boolean isEditing = false;

    @FXML
    void onSalvar(ActionEvent event) {
        try {
            String nomeProduto = NomeText.getText();
            String localizacao = LocText.getText();

            if (nomeProduto.isEmpty() || localizacao.isEmpty()) {
                exibirAlerta(AlertType.ERROR, "Erro", "Preencha todos os campos!");
                return;
            }
            Produto produto = new Produto();
            produto.setEmpresa(GlobalContext.getCurrentFuncionario().getEmpresa());
            produto.setLocalizacao(localizacao);
            produto.setNome(nomeProduto);
            
            ProdutoController = new ProdutoController();

            try {

                if (isEditing) {

                    ProdutoController.atualizar(produto);
                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Produto atualizado com sucesso!");
                } else {
                    ProdutoController.cadastrar(produto);
                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Produto cadastrado com sucesso!");
                }
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o produto: " + e.getMessage());
            }
        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro", "Erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public void setEditing(boolean editing) {
        this.isEditing = editing;
    }
}
