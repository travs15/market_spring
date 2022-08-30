package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//se implementa la interface Crudrepository de spring data
//recibe dos parametros, la tabla o clase y el tipo del primary key de esta tabla, id_producto
public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{
//    query method
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

//    No se implementan en el paso con el mapeo
//    Optional<List<Producto>> findByPrecioVentaLessThanEqual(double precioVenta);
//
//    Optional<List<Producto>> findByPrecioVentaMoreThanEqual(double precioVenta);

//sin usar el query method
//    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
//    List<Producto> cualquierNombre(int idCategoria);
}
