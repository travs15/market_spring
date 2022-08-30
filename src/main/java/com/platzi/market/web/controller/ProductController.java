package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

//    Manera sin manejar los http response
//    @GetMapping("/all")
//    public List<Product> getAll(){
//        return productService.getAll();
//    }

//   Como se devuelve un cuerpo de la resuesta, en el return
//    el primer parametro es el cuerpo de la respuesta
    @GetMapping("/all")
    @ApiOperation("Get all supermaket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public Optional<Product> getProduct(@PathVariable("id") int productId){
//        return productService.getProduct(productId);
//    }

    @GetMapping("/{id}")
    @ApiOperation("Search product by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")
                                                  @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


//    @GetMapping("/category/{categoryId}")
//    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
//        return productService.getByCategory(categoryId);
//    }
    @GetMapping("/category/{categoryId}")
    @ApiOperation("Search category by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "The id of the category", required = true, example = "1")
                                                            @PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping("/save")
//    public Product save(@RequestBody Product product){
//        return productService.save(product);
//    }

    @PostMapping("/save")
    @ApiOperation("Save product")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

//    @DeleteMapping("/delete/{id}")
//    public boolean delete(@PathVariable("id") int productId){
//        return productService.delete(productId);
//    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete product by Id")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity delete(@ApiParam(value = "The id of the product", required = true, example = "7")
                                     @PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
