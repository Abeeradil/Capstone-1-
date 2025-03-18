package org.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "id cannot be empty")
    public String productId ;

    @NotEmpty(message = "name must be not empty")
    @Size( min = 3 , message = "size of name cannot be less than 3 ")
    public String name ;

    @NotNull(message = "price cannot be empty")
    @Positive(message = "price must be positive number")
    public int price;

    @NotEmpty(message = "categoryId cannot be empty")
    public  String categoryId;

}
