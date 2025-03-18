package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Service.MerchantStockService;
import org.example.capstone1.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    //Q6
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> newProduct= productService.getProduct();
        return ResponseEntity.ok(newProduct);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct (@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.ok("added successfully");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@RequestBody @Valid Product product,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdate = productService.updateProduct(id, product);
        if (isUpdate){
            return ResponseEntity.ok("updated successfully");
        }
        return ResponseEntity.badRequest().body("not found");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean isDelete = productService.deleteProduct(id);
        if (isDelete){
            return ResponseEntity.ok("deleted successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }
//extra endpoints1
    @GetMapping("/sorted")
    public ResponseEntity getSorting(){
        return ResponseEntity.ok(productService.productOrder());
    }
    //extra endpoints4
    @GetMapping("/calculate/{productId}/{quantity}")
    public double getDiscountedPrice(@PathVariable String productId, @PathVariable int quantity) {
        return productService.calculateDiscount(productId, quantity);
    }







}
