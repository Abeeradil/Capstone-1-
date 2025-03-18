package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class User {

    @NotEmpty(message = "id cannot be empty")
    public String id;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5 , message = "size of password cannot be less than 5")
    public String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, message = "size of password cannot be less than 6")
    //@Pattern(regexp = "^/[A-zZ-a][0-9]/$")
    public String password;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email is not valid")
    public String email;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "Admin|Costomer")
    public String role;

    @NotNull(message = "balance cannot be null")
    @Positive(message = "balance cannot be empty")
    public double balance;
}
