package br.cefetmg.view;

import br.cefetmg.controller.FuncionarioController;
import br.cefetmg.entidades.Funcionario;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;
import javafx.scene.control.Label;

public class ListarFuncionariosController {
 
    private App app;
    
    @FXML
    private Label label;

    @FXML
    private ListView<String> funcionarioListView; 
    @FXML
    private TextArea detalhesFuncionarioText; 

    @FXML
    private Button voltarButton;

    private FuncionarioController funcionarioController;
    private List<Funcionario> funcionariosList; 
    
    private void  setApp (App app){
        this.app  = app;
    }

    public void initialize() {
        funcionarioController = new FuncionarioController();  
        carregarFuncionarios(); 
    }
    private void carregarFuncionarios() {
        funcionariosList = funcionarioController.listar();  
        if(funcionariosList == null) {
            System.out.println("oasjdfoihadsfodsnfoçiadsj null");
        } else if(funcionariosList.isEmpty()) {
            System.out.println("fdfoajfisjfsodfjiasfjf empty");
        } else {
            ObservableList<String> funcionarioNomes = FXCollections.observableArrayList();

            for (Funcionario funcionario : funcionariosList) {
                funcionarioNomes.add(funcionario.getNome());  
            }

            funcionarioListView.setItems(funcionarioNomes);  

            funcionarioListView.setOnMouseClicked((MouseEvent event) -> {
                mostrarDetalhesFuncionario(funcionarioListView.getSelectionModel().getSelectedIndex());
            });
        }
    }

    private void mostrarDetalhesFuncionario(int index) {
        if (index >= 0 && index < funcionariosList.size()) {
            Funcionario funcionarioSelecionado = funcionariosList.get(index); 
            StringBuilder detalhes = new StringBuilder();
            
            detalhes.append("Nome: ").append(funcionarioSelecionado.getNome()).append("\n");
            detalhes.append("Telefone: ").append(funcionarioSelecionado.getTelefone()).append("\n");
            

            detalhesFuncionarioText.setText(detalhes.toString());  
        }
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    
    }
}
