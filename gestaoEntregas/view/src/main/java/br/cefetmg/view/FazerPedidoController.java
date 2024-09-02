package br.cefetmg.view;

import br.cefetmg.controller.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import br.cefetmg.entidades.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FazerPedidoController {

    private App app;
    
    @FXML
    private ChoiceBox clienteSelect;

    @FXML
    private ListView<ItemPed> itensListView;

    @FXML
    private ChoiceBox produtoSelect;

    @FXML
    private TextField quantidadeText;

    @FXML
    private TextField valorUnitarioText;
    
    @FXML
    private Button adicionarItemButton;

    private ClienteController clienteController;

    private PedidoController pedidoController;
    
    private ProdutoController produtoController;
    
    private Pedido pedido;
    
    private List<Produto> listaProduto;
    
    private List<Cliente> listaCliente;
    
    private ObservableList<ItemPed> listaObsItens;
    
    private boolean edtItem = false;
    
    private int indexItem;

    public void setApp(App app) {
        this.app = app;
    }
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        pedido = new Pedido();
        pedido.setAtendente(GlobalContext.getCurrentFuncionario());
        ArrayList<ItemPed> l = new ArrayList<>();
        pedido.setItens(l);
        listaObsItens = FXCollections.observableArrayList();
        
        clienteController = new ClienteController();
        produtoController = new ProdutoController();
        

        listaCliente = clienteController.listar();
        clienteSelect.getItems().addAll(listaCliente);
        clienteSelect.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente.getNome(); 
            }

            @Override
            public Cliente fromString(String string) {
                return null; 
            }
        });
        if(!listaCliente.isEmpty()) clienteSelect.setValue(listaCliente.get(0));

        
        listaProduto = produtoController.listar();
        produtoSelect.getItems().addAll(listaProduto);
        produtoSelect.setConverter(new StringConverter<Produto>() {
            @Override
            public String toString(Produto produto) {
                return produto.getNome();
            }

            @Override
            public Produto fromString(String string) {
                return null;
            }
        });
        if(!listaProduto.isEmpty()) produtoSelect.setValue(listaProduto.get(0));
        
        edtItem = false;
    }

    
    @FXML
    private void adicionarItem(ActionEvent event) throws IOException {
        if(!edtItem) {
            Produto produto = (Produto) produtoSelect.getSelectionModel().getSelectedItem();
            int q = Integer.parseInt(quantidadeText.getText());
            double pUnit = Double.parseDouble(valorUnitarioText.getText());

            ItemPed i = new ItemPed();
            i.setPedido(pedido);
            i.setProduto(produto);
            i.setQuantidade(q);
            i.setValorUnitario(pUnit);
            pedido.getItens().add(i);

            listaObsItens.add(i);
            itensListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<ItemPed>() {
                @Override
                public String toString(ItemPed i) {
                    return i.getQuantidade() + " x " + i.getProduto().getNome() + " (R$" + i.getValorUnitario() + ")";
                }

                @Override
                public ItemPed fromString(String string) {
                    return null;
                }
            }));
            itensListView.setItems(listaObsItens);
            
        } else {
            Produto produto = (Produto) produtoSelect.getSelectionModel().getSelectedItem();
            int q = Integer.parseInt(quantidadeText.getText());
            double pUnit = Double.parseDouble(valorUnitarioText.getText());
            
            pedido.getItens().get(indexItem).setProduto(produto);
            pedido.getItens().get(indexItem).setQuantidade(q);
            pedido.getItens().get(indexItem).setValorUnitario(pUnit);
            
            listaObsItens.get(indexItem).setProduto(produto);
            listaObsItens.get(indexItem).setQuantidade(q);
            listaObsItens.get(indexItem).setValorUnitario(pUnit);

            adicionarItemButton.setText("Adicionar ao pedido");
            itensListView.refresh();
        }
        
            itensListView.setOnMouseClicked((MouseEvent e) -> {
                    atualizaItem(itensListView.getSelectionModel().getSelectedItem(), 
                            itensListView.getSelectionModel().getSelectedIndex());
            });
        produtoSelect.setValue(0);
        quantidadeText.setText("");
        valorUnitarioText.setText("");
        edtItem = false;
        indexItem = -1;
    }
    
    private void atualizaItem(ItemPed i, int index) {
        produtoSelect.setValue(i.getProduto());
        quantidadeText.setText(String.valueOf(i.getQuantidade()));
        valorUnitarioText.setText(String.valueOf(i.getValorUnitario()));
        
        edtItem = true;
        adicionarItemButton.setText("Atualizar item");
        indexItem = index;
    }
    
//    private void printAll() {
//        System.out.println("======================pedido:==========================");
//        for(ItemPed i : pedido.getItens()) {
//            printItem(i);
//        }
//        System.out.println("");
//        System.out.println("====================Obs List ===============================");
//        for(ItemPed i : listaObsItens) {
//            printItem(i);
//        }
//        System.out.println("");
//        System.out.println("=========================list view===============================");
//        for(ItemPed i : itensListView.getItems()) {
//            printItem(i);
//        }
//    }
//    
//    private void printItem(ItemPed i) {
//        System.out.println("----   item  ----");
//        System.out.println(i.getProduto().getNome());
//        System.out.println(i.getQuantidade());
//        System.out.println(i.getValorUnitario());
//    } 
    
    @FXML
    private void salvarPedido(ActionEvent event) throws IOException {
        pedidoController = new PedidoController();
        try {
            pedido.setCliente((Cliente) clienteSelect.getSelectionModel().getSelectedItem());
            pedido.setStatus(StatusPedido.EM_PREPARO);
            pedido.setData(new Date());
            double total = 0;
            for(ItemPed i : pedido.getItens()) {
                total += i.getValorUnitario() * i.getQuantidade();
            }
            pedido.setValorTotal(total);
            
            if(pedido.getItens().isEmpty()) {
                exibirAlerta(Alert.AlertType.WARNING, "Warning", "Não há itens no pedido!");
            } else {
                pedidoController.cadastrar(pedido);
                exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Pedido cadastrado com sucesso!");
            }
            
        } catch(Exception e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o pedido: " + e.getMessage());
        }
    }
    
    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    
    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        app.setRoot("MenuInicial");
    
    }
}
