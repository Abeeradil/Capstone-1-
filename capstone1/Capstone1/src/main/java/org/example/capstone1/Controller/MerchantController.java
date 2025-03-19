package org.example.capstone1.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Service.MerchantService;
import org.example.capstone1.Service.ProductService;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        ArrayList<Merchant> newMerchant = merchantService.getMerchant();
        return ResponseEntity.ok(newMerchant);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.ok("added successfully");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant (@PathVariable String id , @RequestBody @Valid Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdate = merchantService.updateMerchant(id, merchant);
        if (isUpdate){
            return ResponseEntity.ok("updated successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean isDelete = merchantService.deleteMerchant(id);
        if (isDelete){
            return ResponseEntity.ok("deleted successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }







}
