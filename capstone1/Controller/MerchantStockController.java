package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Service.MerchantStockService;
import org.example.capstone1.Service.ProductService;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> newMerchantStock = merchantStockService.getMerchantStock();
        return ResponseEntity.ok(merchantStockService);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.ok("added successfully");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdate) {
            return ResponseEntity.ok("updated successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteMerchantStock(@PathVariable String id) {

        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.ok("deleted successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }
    @PutMapping("/added/{productId}/{merchantId}/{stock}")
    public ResponseEntity<String> addStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int stock) {
        boolean isAdded = merchantStockService.addStock(productId, merchantId, stock);
        if (isAdded) {
            return ResponseEntity.ok("Stock added successfully");
        }
        return ResponseEntity.badRequest().body("ProductId or MerchantId not found");
    }
    //Q12
    @PostMapping("/buy/{id}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String id, String productId ,String merchantId) {

        boolean isValid = merchantStockService.checkValid(productId);
        if (!isValid) {
            return ResponseEntity.badRequest().body("Invalid product ID");
        }
        boolean isValide = merchantStockService.checkValide(merchantId);
        if (!isValide) {
            return ResponseEntity.badRequest().body("Invalid  merchant ID");
        }

        boolean isInStock = merchantStockService.isInStock(productId, merchantId);
        if (!isInStock) {
            return ResponseEntity.badRequest().body("Product is not in stock");
        }

        double price = productService.getPrice(productId);
        boolean haveMoney = userService.deductPrise(id, price);
        if (!haveMoney) {
            return ResponseEntity.badRequest().body("You don't have enough money");
        }
        merchantStockService.deductStock(productId, merchantId);
        return ResponseEntity.ok("Purchase successfully");
    }


     // Extra endpoint for returning a product
    @PostMapping("/return/{id}/{productId}/{merchantId}")
    public ResponseEntity<String> returnProduct(@PathVariable String id, @PathVariable String productId, @PathVariable String merchantId) {
        boolean isReturn = merchantStockService.returnProduct(id, productId, merchantId);
        if (isReturn) {
            return ResponseEntity.ok("Product returned successfully!");
        }
        return ResponseEntity.badRequest().body("Return failed. Try again.");
    }

    // Extra endpoint for getting low stock products
    @GetMapping("/lowstock")
    public ResponseEntity<ArrayList<MerchantStock>> getLowStockOfProduct() {
        ArrayList<MerchantStock> lowStockProducts = merchantStockService.lowStockProducts();
        return ResponseEntity.ok(lowStockProducts);
    }

}




