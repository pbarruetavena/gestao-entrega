package br.cefetmg.view;

import br.cefetmg.controller.FuncionarioController;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Perfil;
import br.cefetmg.entidades.TipoPerfil;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;

public class CadastrarFuncionarioController {

    private App app;
    
    

    @FXML
    private RadioButton Administrador;

    @FXML
    private RadioButton Atendente;

    @FXML
    private RadioButton Entregador;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private TextField TelText;
    
    @FXML
    private Label TelLabel;

    @FXML
    private Button Salvar;

    @FXML
    private Label SenhaLabel;

    @FXML
    private TextField SenhaText;

    @FXML
    private TextField EmailText;

    private FuncionarioController funcionarioController = new FuncionarioController();
    private boolean isEditing = false;

    private void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        Atendente.setToggleGroup(toggleGroup);
        Administrador.setToggleGroup(toggleGroup);
        Entregador.setToggleGroup(toggleGroup);
    }

    @FXML
    void onSalvar(ActionEvent event) {
        try {
            String nome = NomeText.getText();
            String senha = SenhaText.getText();
            String email = EmailText.getText();
            String telefone = TelText.getText();

            ArrayList<Perfil> perfis = new ArrayList<>();
            if (Administrador.isSelected()) {
                Perfil p = new Perfil();
                p.setTipo(TipoPerfil.ADMINISTRADOR);
                perfis.add(p);
            }
            if (Atendente.isSelected()) {
                Perfil p = new Perfil();
                p.setTipo(TipoPerfil.ATENDENTE);
                perfis.add(p);
            }
            if (Entregador.isSelected()) {
                Perfil p = new Perfil();
                p.setTipo(TipoPerfil.ENTREGADOR);
                perfis.add(p);
            }

            if (nome.isEmpty() || senha.isEmpty() || email.isEmpty() || telefone.isEmpty() || perfis.isEmpty()) {
                exibirAlerta(AlertType.ERROR, "Erro", "Por favor, preencha todos os campos!");
                return;
            }

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setSenha(senha);
            funcionario.setEmail(email);
            funcionario.setTelefone(telefone);
            funcionario.setEmpresa(GlobalContext.getCurrentFuncionario().getEmpresa());
            for (Perfil p : perfis) {
                p.setFuncionario(funcionario);
            }
            funcionario.setPerfis(perfis);

            try {
                if (isEditing) {
                    funcionarioController.atualizar(funcionario);
                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
                } else {
                    funcionarioController.cadastrar(funcionario);
                    exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
                }
            } catch (Exception e) {
                exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o funcionário: " + e.getMessage());
            }

        } catch (Exception e) {
            exibirAlerta(AlertType.ERROR, "Erro", "Erro ao cadastrar o funcionário: " + e.getMessage());
        }
    }

    private void exibirAlerta(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setEditing(boolean editing) {
        this.isEditing = editing;
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    }
}
