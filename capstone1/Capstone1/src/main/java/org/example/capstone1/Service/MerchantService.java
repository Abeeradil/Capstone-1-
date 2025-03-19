package org.example.capstone1.Service;

import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {


    ArrayList<Merchant> newMerchant = new ArrayList<>();

    public ArrayList getMerchant(){
        return newMerchant;
    }

    public void addMerchant(Merchant merchant){
        newMerchant.add(merchant);
    }

    public boolean updateMerchant (String id ,Merchant merchant){
        for (Merchant upd : newMerchant){
            if (upd.getMerchantId().equalsIgnoreCase(id)){
                newMerchant.set(newMerchant.indexOf(upd),merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant (String id){
        for (Merchant del : newMerchant){
            if (del.getMerchantId().equalsIgnoreCase(id)){
                newMerchant.remove(del);
                return true;
            }
        }
        return false;
    }




}
