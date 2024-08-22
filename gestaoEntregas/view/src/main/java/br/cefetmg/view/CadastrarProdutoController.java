package br.cefetmg.view;

//import br.cefetmg.controller.ProdutoController;
//import br.cefetmg.entidades.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarProdutoController {

    @FXML
    private Label LocLabel;

    @FXML
    private TextField LocText;

    @FXML
    private Label NomeLabel;

    @FXML
    private TextField NomeText;

    @FXML
    private Button Salvar;

    @FXML
    private Label TituloProduto;
}

//    private ProdutoController ProdutoController = new ProdutoController(); 
//    private boolean isEditing = false; 
//    @FXML
//    void onSalvar(ActionEvent event) {
//        try {
//            String nomeProduto = NomeText.getText();
//            String localizacao = LocText.getText();
//
//            if (nomeProduto.isEmpty() || localizacao.isEmpty()) {
//                exibirAlerta(AlertType.ERROR, "Erro", "Por favor, preencha todos os campos!");
//                return;
//            }
//
//            Produto produto = new Produto(nomeProduto, localizacao);
//
//           try {
//            if (isEditing) {
//               ProdutoController.atualizarFuncionario(Produto);
//                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário atualizado com sucesso!");
//            } else {
//                ProdutoController.cadastrarFuncionario(Produto);
//                exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
//            }
//        } catch (Exception e) {
//            exibirAlerta(AlertType.ERROR, "Erro", "Ocorreu um erro ao salvar o funcionário: " + e.getMessage());
//        }
//    
//
//            exibirAlerta(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
//
//        } catch (Exception e) {
//            exibirAlerta(AlertType.ERROR, "Erro", "Erro ao cadastrar o funcionário: " + e.getMessage());
//        }
//    }
//
//   private void exibirAlerta(AlertType alertType, String title, String message) {
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
