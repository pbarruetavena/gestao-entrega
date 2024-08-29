package br.cefetmg.view;

import br.cefetmg.controller.ClienteController;
import br.cefetmg.controller.EmpresaController;
import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Empresa;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarClienteController {
    
    private App app;

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
    
    private void  setApp (App app){
        this.app  = app;
    }

    @FXML
    void onSalvar(ActionEvent event) {
        if (NomeText.getText().isEmpty() || BairroText.getText().isEmpty() || 
            LograText.getText().isEmpty() || TelText.getText().isEmpty() || cpfText.getText().isEmpty()) {
            exibirAlerta(AlertType.ERROR, "Erro", "Preencha todos os campos!");
        } else {
            try {
                EmpresaController empresaController = new EmpresaController();
                Empresa empresa = empresaController.selecionar(1); 

                ClienteController clienteController = new ClienteController();
                Cliente cliente = new Cliente();
                cliente.setNome(NomeText.getText());
                cliente.setEndereco(LograText.getText());
                cliente.setBairro(BairroText.getText());
                cliente.setTelefone(TelText.getText());
                cliente.setCpf(cpfText.getText());
                cliente.setEmpresa(empresa); 

                clienteController.cadastrar(cliente);

                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o cliente: " + e.getMessage());
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
        app.setRoot("login");
    }
}
