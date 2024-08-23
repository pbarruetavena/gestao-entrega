
package br.cefetmg.entidades;

/**
 *
 * @author Pedro Gabriel
 */
public enum TipoPerfil {
    ADMINISTRADOR("administrador"),
    ATENDENTE("atendente"),
    ENTREGADOR("entregador");
    
    private String tipo;
    
    TipoPerfil(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
}