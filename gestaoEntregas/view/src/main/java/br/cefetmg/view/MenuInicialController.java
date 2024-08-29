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

/**
 * FXML Controller class
 *
 * @author Pedro Gabriel
 */
public class MenuInicialController implements Initializable {

        private App app;

    @FXML
    private Button SairSis;
    @FXML
    private Button CadastrarCliente;
    @FXML
    private Button ListarCliente;
    @FXML
    private Button FazerPedido;

    public void setApp(App app) {
        this.app = app;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        Funcionario current = GlobalContext.getCurrentFuncionario();
        if(current == null) {
            try {
                app.setRoot("login");
            } catch (IOException ex) {
                Logger.getLogger(MenuInicialController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        CadastrarCliente.setVisible(false);
        ListarCliente.setVisible(false);
        FazerPedido.setVisible(false);
        
        if(current.isAtendente()) {
            System.out.println("dentro do if");
            CadastrarCliente.setVisible(true);
            ListarCliente.setVisible(true);
            FazerPedido.setVisible(true);
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
    private void Sair() throws IOException {
        GlobalContext.setCurrentFuncionario(null);
        app.setRoot("login");
    }
    
}
