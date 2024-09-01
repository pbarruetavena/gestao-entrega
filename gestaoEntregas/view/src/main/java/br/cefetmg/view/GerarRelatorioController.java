/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.view;

import br.cefetmg.controller.EmpresaController;
import br.cefetmg.entidades.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Pedro Gabriel
 */
public class GerarRelatorioController implements Initializable {
    
    private App app;

    @FXML
    private DatePicker dataPickerInicio;

    @FXML
    private Spinner<Integer> horaSpinnerInicio;

    @FXML
    private Spinner<Integer> minutoSpinnerInicio;
    
    @FXML
    private DatePicker dataPickerFim;

    @FXML
    private Spinner<Integer> horaSpinnerFim;

    @FXML
    private Spinner<Integer> minutoSpinnerFim;
    
    @FXML
    private ListView<Funcionario> funcionarioListView;
    
    @FXML
    private ListView<Pedido> pedidoListView;
    
    private EmpresaController empresaController;
    private Relatorio relatorio;
    private Date dateInicio;
    private Date dateFim;
    
    private void  setApp (App app){
        this.app  = app;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        dataPickerInicio.setValue(LocalDate.now());
        dataPickerFim.setValue(LocalDate.now());
        horaSpinnerInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour()));
        horaSpinnerFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour()));
        minutoSpinnerInicio.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute()));
        minutoSpinnerFim.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute()));
    
    }
    
    @FXML
    private void pesquisar(ActionEvent e) {
        
        if(getDatas()) {

            empresaController = new EmpresaController();
            relatorio = empresaController.getRelatorio(GlobalContext.getCurrentFuncionario().getEmpresa(),
                    dateInicio, dateFim);

            ObservableList<Funcionario> listaObsFuncionario = FXCollections.observableArrayList();
            listaObsFuncionario.addAll(relatorio.getEmpresa().getFuncionarios());
            funcionarioListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Funcionario>() {
                @Override
                public String toString(Funcionario i) {
                    if(relatorio.getTotais().containsKey(i))
                        return i.getNome() + ": R$ " + relatorio.getTotais().get(i);
                    else
                        return i.getNome() + ": R$ 0.00";
                }

                @Override
                public Funcionario fromString(String string) {
                    return null;
                }
            }));
            funcionarioListView.setItems(listaObsFuncionario);
            funcionarioListView.setOnMouseClicked((MouseEvent evt) -> {
                    mostrarDetalhesFuncionario(funcionarioListView.getSelectionModel().getSelectedItem());
            });
            
        } else {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Período inválido, confira se os campos estão preenchidos e o início vem antes do final");
        }
    }
    
    private void mostrarDetalhesFuncionario(Funcionario f) {
        ObservableList<Pedido> listaObsPedido = FXCollections.observableArrayList();
        listaObsPedido.addAll(relatorio.getPedidos().get(f));
        pedidoListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Pedido>() {
                @Override
                public String toString(Pedido i) {
                    String operation = "";
                    if(i.getAtendente()!=null && i.getAtendente().getId() == f.getId()) {
                        operation = operation + " /Atendente";
                    }
                    if(i.getEntregador()!= null && i.getEntregador().getId() == f.getId()) {
                        operation = operation + " /Entregador";
                    }
                    
                    return "Data: " + i.getData() + "\n" +
                            "Operação: " + operation + "\n" +
                            "Total: " + i.getValorTotal() + "\n";
                }

                @Override
                public Pedido fromString(String string) {
                    return null;
                }
            }));
        pedidoListView.setItems(listaObsPedido);
    }
    
    private boolean getDatas() {
        LocalDate dataI = dataPickerInicio.getValue();
        int hora = horaSpinnerInicio.getValue();
        int minuto = minutoSpinnerInicio.getValue();
        LocalDateTime localdataInicio = LocalDateTime.of(dataI, LocalTime.of(hora, minuto));
        dateInicio = Date.from(localdataInicio.atZone(ZoneId.systemDefault()).toInstant());
        
        LocalDate dataF = dataPickerFim.getValue();
        hora = horaSpinnerFim.getValue();
        minuto = minutoSpinnerFim.getValue();
        LocalDateTime localdataFim = LocalDateTime.of(dataF, LocalTime.of(hora, minuto));
        dateFim = Date.from(localdataFim.atZone(ZoneId.systemDefault()).toInstant());
        
        return dateInicio.before(dateFim);
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
