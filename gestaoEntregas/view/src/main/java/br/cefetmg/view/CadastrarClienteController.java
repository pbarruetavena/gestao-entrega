package br.cefetmg.view;

import br.cefetmg.controller.ClienteController;
import br.cefetmg.entidades.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarClienteController {

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

    @FXML
    void onSalvar(ActionEvent event) {
        if (NomeText.getText().isEmpty() || BairroText.getText().isEmpty() || 
            LograText.getText().isEmpty() || TelText.getText().isEmpty() || cpfText.getText().isEmpty()) {
            exibirAlerta(AlertType.ERROR, "Erro", "Preencha todos os campos!");
        } else {
            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
        }
    }

    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
