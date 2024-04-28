package com.fackstoreintract.fackstoreinteract.controller;

import com.fackstoreintract.fackstoreinteract.Dtos.ProductRequestDto;
import com.fackstoreintract.fackstoreinteract.Dtos.ProductWrapper;
import com.fackstoreintract.fackstoreinteract.Services.FakeStoreProductService;
import com.fackstoreintract.fackstoreinteract.Services.IProductService;
import com.fackstoreintract.fackstoreinteract.exceptions.ProductNotPresentException;
import com.fackstoreintract.fackstoreinteract.model.Category;
import com.fackstoreintract.fackstoreinteract.model.Product;
import com.fackstoreintract.fackstoreinteract.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ProductController {
    @Autowired
    FakeStoreProductService productService;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/products")
    public List<Product> getAllProducts(){
      return productService.getAllProduct();
    }
    @GetMapping("/products/search")

    public Product getPorductByName(@RequestParam("name") String name){
        return productRepository.findByName(name);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductWrapper> getSingleProduct(@PathVariable("id") Long id){

        Product product;
        try{
            product = productService.getSingleProduct(id);
        }catch (ProductNotPresentException e){
            ProductWrapper productWrapper = new ProductWrapper(null, "Product Doesn't exist");
            return new ResponseEntity<>(
                    productWrapper, HttpStatus.NOT_FOUND);
        }catch (ArithmeticException e){
            ProductWrapper productWrapper = new ProductWrapper(null, "Something went wrong");
            return new ResponseEntity<>(
                    productWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ProductWrapper(product, "Success"), HttpStatus.OK);
    }

    @GetMapping("/product/exception/{id}")
    public ResponseEntity<ProductWrapper> getSingleProductException(@PathVariable("id") Long id)
            throws ProductNotPresentException {

        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(new ProductWrapper(product, "Success"), HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories(){

        return new ArrayList<>();
    }

    @GetMapping("/products/category/{id}")
    public List<Product> getAllProductsInCategory(@PathVariable("id") Long id){

        return new ArrayList<>();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto requestDto){
        return new Product();
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDto requestDto){
        return new Product();
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }
}
