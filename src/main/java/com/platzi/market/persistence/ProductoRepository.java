package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//se realiza una clase en la cual se pueden usar los metodos de la interface
// se llama productoCrudRepository para usarlos sobre la tabla Producto
@Repository
public class ProductoRepository implements ProductRepository {
//    esta anotaciòn se usa para la inyeccion de dependencias
//    no se crea y se inicializa el objeto, si no que se declara asi
//    se debe estar 100% seguro que el objeto es un componente de spring
//    este es del tipo CrudREpository que si vamos al codigo tiene @NoRepositoryBean, es un stereotipo de
//    repositorio de spring, por lo tanto es un componente de spring
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

//    el product mapper tiene indicado asi @Mapper(componentModel = "spring" que es un componente de spring
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {

        return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

// como estaban antes de crear el mapeo para el dominio

//    public List<Producto> getByCategoria(int idCategoria){
//        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
//    }
//
//    public Optional<List<Producto>> getEscasos(int cantidadStock){
////        se quita el estado ya que queremos los que estan en estado true
//        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
//    }
//
//    public Optional<List<Producto>> getProductosMasBaratoQue(int precioVenta){
//        return productoCrudRepository.findByPrecioVentaLessThanEqual(precioVenta);
//    }
//
//    public Optional<List<Producto>> getProductosMasCaroQue(int precioVenta){
//        return productoCrudRepository.findByPrecioVentaMoreThanEqual(precioVenta);
//    }
//
//    public Optional<Producto> getProducto(int idProducto){
//        return productoCrudRepository.findById(idProducto);
//    }
//
//    public Producto save(Producto producto){
//        return productoCrudRepository.save(producto);
//    }
//
//    public void delete(int idProducto){
//        productoCrudRepository.deleteById(idProducto);
//    }
}