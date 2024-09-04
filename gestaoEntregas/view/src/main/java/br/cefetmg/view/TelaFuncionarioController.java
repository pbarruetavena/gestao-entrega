package br.cefetmg.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaFuncionarioController  {

    private App app;

    @FXML
    private Button SairSis;
    @FXML
    private Button Cadastrar;
    @FXML
    private Button Listar;
    @FXML
    private Button FazerPedido;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void FazerPedido() throws IOException {
        app.setRoot("FazerPedido");
    }

    @FXML
    private void ListarClientes() throws IOException {
        app.setRoot("ListarClientes");
    }

    @FXML
    private void CadastrarCliente() throws IOException {
        app.setRoot("CadastrarCliente");
    }

    @FXML
    private void Sair() throws IOException {
        app.setRoot("login");
    }

    private void exibirAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    
}
