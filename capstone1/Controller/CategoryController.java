package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> newCategory = categoryService.getCategory();
       return ResponseEntity.ok(newCategory) ;
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.ok("added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category ,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdete = categoryService.updateCategory(id, category);
        if (isUpdete){
            return ResponseEntity.ok("Updated successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean isDelete = categoryService.deleteCategory(id);
        if (isDelete){
            return ResponseEntity.ok("deleted successfully");
        }
        return ResponseEntity.badRequest().body("not found");
    }




}
