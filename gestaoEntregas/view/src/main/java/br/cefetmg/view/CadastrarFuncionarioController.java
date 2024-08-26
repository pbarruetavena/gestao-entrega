package br.cefetmg.view;

import br.cefetmg.controller.FuncionarioController;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Perfil;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarFuncionarioController {

    @FXML
    private CheckBox Administrador;

    @FXML
    private CheckBox Atendente;

    @FXML
    private CheckBox Entregador;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private Button Salvar;

    @FXML
    private Label SenhaLabel;

    @FXML
    private TextField SenhaText;

    @FXML
    private TextField TelText;

    private FuncionarioController funcionarioController = new FuncionarioController();
    private boolean isEditing = false;

    @FXML
    void onSalvar(ActionEvent event) {

//        try {
//            String nome = NomeText.getText();
//            String senha = SenhaText.getText();
//            String telefone = TelText.getText();
//
//            ArrayList<Perfil> perfil ;
//            if (Administrador.isSelected()) {
//                perfil.add(new Perfil("Administrador"));
//            }
//            if (Atendente.isSelected()) {
//                perfil.add(new Perfil("Atendente"));
//            }
//            if (Entregador.isSelected()) {
//                perfil.add(new Perfil("Entregador"));
//            }
//
//            if (nome.isEmpty() || senha.isEmpty() || telefone.isEmpty() || perfil.isEmpty()) {
//                exibirAlerta(AlertType.ERROR, "Erro", "Por favor, preencha todos os campos!");
//                return;
//            }
//
//            Funcionario funcionario = new Funcionario(nome, senha, telefone,perfil);
//
//            try {
//                if (isEditing) {
//                    funcionarioController.atualizar(funcionario);
//                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
//                } else {
//                    funcionarioController.cadastrar(funcionario);
//                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
//                }
//            } catch (Exception e) {
//                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o funcionário: " + e.getMessage());
//            }
//
//            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
//
//        } catch (Exception e) {
//            exibirAlerta(AlertType.ERROR, "Erro", "Erro ao cadastrar o funcionário: " + e.getMessage());
//        }
//    }
//
//    private void exibirAlerta(AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void setEditing(boolean editing) {
//        this.isEditing = editing;
//    }
//}
    }
}