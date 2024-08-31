package br.cefetmg.view;

import br.cefetmg.entidades.Produto;
import br.cefetmg.controller.ProdutoController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class ListarProdutoController {

    private App app;
    
    @FXML
    private ListView<String> produtoListView;

    @FXML
    private TextArea detalhesProdutoText;

    @FXML
    private Button voltarButton;

    private ProdutoController produtoController;
    
    private List<Produto> produtoList;

    private void  setApp (App app){
        this.app  = app;
    }
    
    public void initialize() {
        produtoController = new ProdutoController();
        this.carregarProdutos();
    }
    
    private void carregarProdutos() {
        produtoController = new ProdutoController();
        produtoList = produtoController.listar();
    
        if(produtoList == null) {
            System.out.println("fjdsuahfusdahfisda ta null");
        } else if(produtoList.isEmpty()) {
            System.out.println("shadfufhsduisdafjshdafj isEmpty");
        } else {
            
            ObservableList<String> produtosNome = FXCollections.observableArrayList();
            for(Produto p : produtoList) {
                produtosNome.add(p.getNome());
            }
            
            produtoListView.setItems(produtosNome);
            
                produtoListView.setOnMouseClicked((MouseEvent e) -> {
                this.mostrarDetalhesProduto(produtoListView.getSelectionModel().getSelectedIndex());
            });
        }
        
    }

    private void mostrarDetalhesProduto(int index) {
        if (index >= 0 && index < produtoList.size()) {
            Produto produto = produtoList.get(index);
            StringBuilder detalhes = new StringBuilder();
            detalhes.append("Nome: ").append(produto.getNome()).append("\n");
            detalhes.append("Localização: ").append(produto.getLocalizacao()).append("\n");

            detalhesProdutoText.setText(detalhes.toString());
        }
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    
    }
}
