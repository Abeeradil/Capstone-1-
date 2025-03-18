package org.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = "id cannot be empty")
    public String merchantId;

    @NotEmpty(message = " name must be empty ")
    @Size(min = 3, message = " size of name cannot be less than 3 ")
    public String name;


}
