package com.platzi.market.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    @EmbeddedId
    private ComprasProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

//    relacion de esta entidad a Compra
//    se coloca directamente el campo correspondiente a la BD de la entidad compra
//    siempre que tengan ManytoOne debe tener en el join column insertable = false, updatable = false
//    esto es con el fin de que no se creen registros desde esta entidad como una nueva compra
//    si no que se cree desde la entidad Compra
    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

//    relacion de esta entidad a Producto
//    se coloca directamente el campo correspondiente a la BD de la entidad producto
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
