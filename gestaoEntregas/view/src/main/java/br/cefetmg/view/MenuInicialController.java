/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.view;

import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Perfil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Pedro Gabriel
 */
public class MenuInicialController implements Initializable {

    private App app;
    
    @FXML
    private Label textBemVindo;

    @FXML
    private Button SairSis;
    @FXML
    private Button CadastrarCliente;
    @FXML
    private Button ListarCliente;
    @FXML
    private Button FazerPedido;
    @FXML
    private Button CadastrarFuncionario;
    @FXML
    private Button CadastrarProduto;
    @FXML
    private Button ListarFuncionario;
    @FXML
    private Button ListarProdutos;
    @FXML
    private Button ListarPedido;

    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funcionario current = GlobalContext.getCurrentFuncionario();
        if (current == null) {
            try {
                app.setRoot("login");
            } catch (IOException ex) {
                Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        textBemVindo.setText("Bem vindo(a), " + current.getNome());

        CadastrarCliente.setVisible(false);
        ListarCliente.setVisible(false);
        FazerPedido.setVisible(false);
        CadastrarFuncionario.setVisible(false);
        CadastrarProduto.setVisible(false);
        ListarFuncionario.setVisible(false);
        ListarProdutos.setVisible(false);
        ListarPedido.setVisible(false);

        if (current.isAtendente()) {
            CadastrarCliente.setVisible(true);
            ListarCliente.setVisible(true);
            FazerPedido.setVisible(true);
        }

        if (current.isAdministrador()) {
            CadastrarCliente.setVisible(true);
            ListarCliente.setVisible(true);
            FazerPedido.setVisible(true);
            CadastrarFuncionario.setVisible(true);
            CadastrarProduto.setVisible(true);
            ListarFuncionario.setVisible(true);
            ListarProdutos.setVisible(true);
            ListarPedido.setVisible(true);

        }
        if (current.isEntregador()) {
          
            ListarPedido.setVisible(true);

        }
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
    private void CadastrarFuncionario() throws IOException {
        app.setRoot("CadastrarFuncionario");
    }
    @FXML
    private void CadastrarProduto() throws IOException {
        app.setRoot("CadastrarProduto");
    }
    @FXML
    private void ListarFuncionario() throws IOException {
        app.setRoot("ListarFuncionario");
    }
    @FXML
    private void ListarProdutos() throws IOException {
        app.setRoot("ListarProduto");
    }
    
    @FXML
    private void ListarPedidos() throws IOException {
        app.setRoot("ListarPedido");
    }
    
    @FXML
    private void GerarRelatorio() throws IOException {
        app.setRoot("GerarRelatorio");
    }
    

    @FXML
    private void Sair() throws IOException {
        GlobalContext.setCurrentFuncionario(null);
        app.setRoot("login");
    }

}
