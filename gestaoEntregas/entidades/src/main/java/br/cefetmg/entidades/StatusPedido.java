
package br.cefetmg.entidades;

/**
 *
 * @author Pedro Gabriel
 */
public enum StatusPedido {
    EM_PREPARO("Em preparo"),
    EM_ENTREGA("Em entrega"),
    ENTREGUE("Entregue");
    
    private String status;
    
    StatusPedido(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
}