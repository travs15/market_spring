package com.platzi.market.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

//    campo que se va a relacionar con el cliente
    @Column(name = "id_cliente")
    private String idCliente;

    private LocalDateTime fecha;

    @Column(name = "medio_pago")
    private String medioPago;

    private String comentario;

    private String estado;

//    relacion de muchos a uno con la entidad cliente
//    se relaciona con el cammpo id_cliente de esta entidad
    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

//    el campo en la tabla ComprasProducto por donde se unen
//    con el nombre de la variable
    @OneToMany(mappedBy = "producto")
    private List<ComprasProducto> productos;

    public Integer getIdCompra() {
        return idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
