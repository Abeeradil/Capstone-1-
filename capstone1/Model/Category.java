package org.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "id must be not empty")
    public String id ;


    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3,message =" size of name cannot be less than 3 ")
    public String name;


}
