package br.cefetmg.view;

import br.cefetmg.controller.FuncionarioController;
import br.cefetmg.entidades.Funcionario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    private App app;


    @FXML
    private TextField TextSenha;

    @FXML
    private TextField TextUsuario;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelUsuario;
    
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void handleEnviar(ActionEvent event) {
        System.out.println("chegou no metodo evt/kefoasjdfgipdsajfoksanfoiasfoia");

        String email = TextUsuario.getText();
        String senha = TextSenha.getText();
        
        if (email.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Campos vazios", "Por favor, preencha todos os campos.");
        } else {
            FuncionarioController ctrl = new FuncionarioController();
            Funcionario current = ctrl.validarLogin(email, senha);
            if(current == null) {
                showAlert(Alert.AlertType.ERROR, "Credenciais incorretas", "Por favor, verifique as credenciais.");
            } else {
                GlobalContext.setCurrentFuncionario(current);
                try {
                    app.setRoot("MenuInicial");
                } catch (IOException ex) {
                    System.out.println("Erro em mandar de uma tela pra outra");
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void switchToPrimary(){
        
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}