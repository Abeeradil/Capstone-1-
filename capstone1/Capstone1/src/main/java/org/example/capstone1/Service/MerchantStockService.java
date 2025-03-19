package org.example.capstone1.Service;

import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    private final UserService userService;
    private final ProductService productService;
    ArrayList<MerchantStock> newMerchantStock = new ArrayList<>();

    public MerchantStockService(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }


    public ArrayList getMerchantStock() {
        return newMerchantStock;

    }

    public void addMerchantStock(MerchantStock merchantStock) {

        newMerchantStock.add(merchantStock);
    }

    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (MerchantStock upd : newMerchantStock) {
            if (upd.getId().equalsIgnoreCase(id)) {
                newMerchantStock.set(newMerchantStock.indexOf(upd), merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id) {
        for (MerchantStock del : newMerchantStock) {
            if (del.getId().equalsIgnoreCase(id)) {
                newMerchantStock.remove(del);
                return true;
            }
        }
        return false;
    }



    public boolean addStock(String productId, String merchantId, int stock) {
        for (MerchantStock added : newMerchantStock) {
            if (added.getProductId().equalsIgnoreCase(productId) &&
                    added.getMerchantId().equalsIgnoreCase(merchantId)) {
                added.setStock(added.getStock() + stock);
                return true;
            }
        }
        return false;
    }

//    public boolean isValid (String id){
//        for (MerchantStock check : newMerchantStock){
//            if (check.getId().equalsIgnoreCase(id)){
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean checkValid(String productId) {
        for (MerchantStock check : newMerchantStock) {
            if (check.getProductId().equalsIgnoreCase(productId)){
                return true;
            }
        }
        return false;
    }
    public boolean checkValide( String merchantId) {
        for (MerchantStock check : newMerchantStock) {
            if (check.getMerchantId().equalsIgnoreCase(merchantId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInStock(String productId, String merchantId) {
        for (MerchantStock isIn : newMerchantStock) {
            if (isIn.getProductId().equals(productId) &&
                    isIn.getMerchantId().equals(merchantId)) {

                    return isIn.getStock() > 0;
                }

            }
        return false;
    }

    public boolean deductStock(String productId, String merchantId) {
        for (MerchantStock stock : newMerchantStock) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                stock.setStock(stock.getStock() - 1);
                return true;
            }
        }
        return false;
    }


   // extra endpoint 2
    public boolean returnProduct(String userId, String productId, String merchantId ) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return false;
        }
        for (MerchantStock ret : newMerchantStock) {
            if (ret.getProductId().equalsIgnoreCase(productId) && ret.getMerchantId().equalsIgnoreCase(merchantId)) {
                double productPrice = productService.getProductPrice(productId);
                if (productPrice == -1){
                    return false;
                }
                user.setBalance(user.getBalance() + productPrice);
            }
            ret.setStock(ret.getStock()+1);
return true;

        }
        return false;
    }
    //extra endpoint 3
    public ArrayList lowStockProducts() {
        ArrayList<MerchantStock> lowStockProducts = new ArrayList<>();
        for (MerchantStock low : newMerchantStock) {
            if (low.getStock() < 15) {
                lowStockProducts.add(low);
            }
        }
        return lowStockProducts;
    }
}




