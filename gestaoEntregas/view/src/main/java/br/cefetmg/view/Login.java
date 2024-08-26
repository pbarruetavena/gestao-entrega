package br.cefetmg.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {




    @FXML
    private TextField TextSenha;

    @FXML
    private TextField TextUsuario;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelUsuario;

    @FXML
    void handleEnviar(ActionEvent event) {

        String usuario = TextUsuario.getText();
        String senha = TextSenha.getText();
        
        if (usuario.isEmpty() || senha.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Campos vazios", "Por favor, preencha todos os campos.");
        } else {
            
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Login realizado com sucesso!");
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