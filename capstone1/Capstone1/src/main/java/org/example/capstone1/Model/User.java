package org.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class User {

    ArrayList<String> couponCodes = new ArrayList<>();
    ArrayList<Double> couponValues = new ArrayList<>();
    private ArrayList<String> transactions = new ArrayList<>();

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
    private String targetUserId;

    @NotEmpty(message = "id cannot be empty")
    public String userId;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5, message = "size of password cannot be less than 5")
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


    public void addCoupons(String couponCode, double couponValue) {
        this.couponCodes.add(couponCode);
        this.couponValues.add(couponValue);
    }

    public void addCoupons(ArrayList<String> couponCodes, ArrayList<Double> couponValues) {
    }


//
}
