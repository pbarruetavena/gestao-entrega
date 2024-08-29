module br.cefetmg.dao {
    requires br.cefetmg.entidades;
    requires java.persistence;

    opens br.cefetmg.dao;
    exports br.cefetmg.dao;
}
