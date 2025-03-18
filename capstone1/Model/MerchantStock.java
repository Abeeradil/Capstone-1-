package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id must be not empty")
    public String id;

    @NotEmpty(message = "productId cannot be empty")
    public String productId;

    @NotEmpty(message = "merchantId must be not empty")
    public String merchantId;

    @NotNull(message = "stock cannot be null ")
    @Min(value = 10,message = "stock most be 10 at start")
    public int stock;

}
