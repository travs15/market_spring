package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

//se implementa la interface Crudrepository de spring data
//recibe dos parametros, la tabla o clase y el tipo del primary key de esta tabla, id_producto
public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{ }
