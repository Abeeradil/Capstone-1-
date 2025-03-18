package org.example.capstone1.Service;

import jakarta.validation.Valid;
import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> newProduct = new ArrayList<>();

    public ArrayList getProduct() {
        return newProduct;
    }

    public void addProduct(Product product) {
        newProduct.add(product);
    }

    public boolean updateProduct(String productId, Product product) {
        for (Product upd : newProduct) {
            if (upd.getProductId().equalsIgnoreCase(productId)) {
                newProduct.set(newProduct.indexOf(upd), product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String productId) {
        for (Product del : newProduct) {
            if (del.getProductId().equalsIgnoreCase(productId)) {
                newProduct.remove(del);
                return true;
            }
        }
        return false;
    }


    public double getPrice(@Valid String productId) {

        return 0;
    }
//extra endpoints1
    public ArrayList<Product> productOrder() {

        for (int i = 0; i < newProduct.size() - 1; i++) {
            for (int j = 0; j < newProduct.size() - i - 1; j++) {
                if (newProduct.get(j).getPrice() > newProduct.get(j + 1).getPrice()) {

                    Product pot = newProduct.get(j);
                    newProduct.set(j, newProduct.get(j + 1));
                    newProduct.set(j + 1, pot);
                }
            }
        }
        return newProduct;
    }
    ////extra endpoints4

    public double calculateDiscount(String productId, int quantity) {
        for (Product cal : newProduct) {
            if (cal.getProductId().equals(productId)) {
                double price = cal.getPrice() * quantity;
                if (quantity > 3) {
                    price *= 0.9;
                }
                return price;
            }
        }
        return 0.0;
    }


}
