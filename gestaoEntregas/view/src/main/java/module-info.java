module br.cefetmg.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires controller;
    requires entidades;
    requires java.instrument;
    requires java.sql;

    opens br.cefetmg.view to javafx.fxml;
    exports br.cefetmg.view;
}