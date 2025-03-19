package org.example.capstone1.Service;

import jakarta.validation.Valid;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

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

    public boolean deleteProduct(String productId){
        for (Product del :newProduct){
            if (del.getProductId().equalsIgnoreCase(productId));
            newProduct.remove(newProduct.indexOf(del));
            return true;
        }
        return false;
    }

    public double getProductPrice(String productId) {
        for (Product get : newProduct) {
            if (get.getProductId().equalsIgnoreCase(productId)) {
               return get.getPrice();
            }
        }
        return 0.0;
    }




//extra endpoints1
    public ArrayList productOrder(String categoryId) {

       ArrayList<Product> newProducts = new ArrayList<>();
        for (Product pro : newProduct) {
            if (pro.getCategoryId().equalsIgnoreCase(categoryId)) {
                newProduct.add(pro);
            }
    }
        for (int i = 0; i < newProducts.size() - 1; i++) {
            for (int j = 0; j < newProducts.size() - i - 1; j++) {
                if (newProducts.get(j).getPrice() > newProducts.get(j + 1).getPrice()) {
                    Product temp = newProducts.get(j);
                    newProducts.set(j, newProducts.get(j + 1));
                    newProducts.set(j + 1, temp);
                }
            }
        }

            return newProducts;
    }





    //extra endpoints4

    public String calculateDiscount(String productId, int quantity) {
        for (Product product : newProduct) {
            if (product.getProductId().equals(productId)) {
                double price = product.getPrice() * quantity;
                String discountCode = "";

                if (quantity >= 5 && quantity <= 10) {
                    price *= 0.9;
                } else if (quantity > 10) {
                    price *= 0.5;
                    discountCode = "loloAa1048";
                }

                if (!discountCode.isEmpty()) {
                    return "Final Price: " + price + " | Discount Code: " + discountCode;
                }
                return "Final Price: " + price;
            }
        }
        return "Product not found";
    }

}
