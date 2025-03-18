package org.example.capstone1.Service;

import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> newUser = new ArrayList<>();
//Q10
    public ArrayList getUser(){
        return newUser;
    }

    public void addUser (User user){
        newUser.add(user);
    }


    public boolean updateUser (String id,User user){
        for (User upd : newUser){
            if (upd.getId().equalsIgnoreCase(id)){
                newUser.set(newUser.indexOf(upd),user);
                        return true;
            }
        }
        return false;
    }

    public boolean deleteUser (String id){
        for (User del : newUser){
            if (del.getId().equalsIgnoreCase(id)){
                newUser.remove(del);
                return true;
            }
        }
        return false;
    }
    //Q12
public boolean deductPrise (String id ,double price){
        for (User ded :newUser){
            if (ded.getId().equals(id)){
                if (ded.getBalance() >= price){
                    ded.setBalance(ded.getBalance() - price);
                    return true;
                }
                return false;
            }
        }
        return false;
    }


    public User getUserById(String id) {

        return null;
    }

    public String isValidUser(String id) {
        for (User user : newUser) {
            if (user.equals(id)) {
                return "valid";
            }
        }
        return "not valid";
    }

    public void addGift(String id, double amount) {
        for (User user : newUser) {
            if (user.id.equals(id)) {
                user.balance += amount;
                break;
            }

}

