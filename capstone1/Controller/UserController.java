package org.example.capstone1.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.User;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

@GetMapping("/get")
    public ResponseEntity getUser (){
        ArrayList<User> newUser = userService.getUser();
        return ResponseEntity.ok(newUser);
    }
@PostMapping("/add")
    public ResponseEntity addUser (@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
        }

        userService.addUser(user);
        return ResponseEntity.ok("added successfully");

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser (@PathVariable String id ,@RequestBody @Valid User user,Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }
    boolean isUpdate = userService.updateUser(id, user);
    if (isUpdate){
        return ResponseEntity.ok("update successfully");
    }
    return ResponseEntity.badRequest().body("not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser (@PathVariable String id ){
    boolean isUpdate = userService.deleteUser(id);
    if (isUpdate){
        return ResponseEntity.ok("deleted successfully");
    }
    return ResponseEntity.badRequest().body("not found");
    }
//
//    private boolean isLoggedIn = false;
//
//    @PostMapping("/login/{id}")
//    public ResponseEntity login(@PathVariable String id) {
//        if (userService.isValidUser(id)) {
//            isLoggedIn = true;
//            return ResponseEntity.ok("User logged in successfully");
//        }
//        return ResponseEntity.badRequest().body("Invalid user ID");
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpSession session) {
//        userService.logout(session);
//        return ResponseEntity.ok("User logged out successfully");
//    }
}

@PostMapping("/giftCard/{id}")
public ResponseEntity<String> giftCard(@PathVariable String id) {
    double giftAmount = 100.0;

    if (userService.isValidUser(id)) {

        userService.addGift(id, giftAmount);
        return ResponseEntity.ok("added" + giftAmount + " successfully ");
    } else {
        return ResponseEntity.badRequest().body("notfound");
    }
}





