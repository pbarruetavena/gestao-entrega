module br.cefetmg.view {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.cefetmg.view to javafx.fxml;
    exports br.cefetmg.view;
}
