package org.example.capstone1.Service;

import org.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> newUser = new ArrayList<>();


    //Q10
    public ArrayList getUser() {
        return newUser;
    }

    public void addUser(User user) {
        newUser.add(user);
    }


    public boolean updateUser(String userId, User user) {
        for (User upd : newUser) {
            if (upd.getUserId().equalsIgnoreCase(userId)) {
                newUser.set(newUser.indexOf(upd), user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        for (User del : newUser) {
            if (del.getUserId().equalsIgnoreCase(userId)) {
                newUser.remove(del);
                return true;
            }
        }
        return false;
    }

    //Q12
    public boolean deductPrise(String userId, double price) {
        for (User ded : newUser) {
            if (ded.getUserId().equals(userId)) {
                if (ded.getBalance() >= price) {
                    ded.setBalance(ded.getBalance() - price);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean isValidUser(String userId) {
        return getUserById(userId) != null;
    }


    public String addCouponToUser(User  adminUser) {
        if (!"Admin".equals(adminUser.getRole())) {
            return "Only Admin can add coupons.";
        }

        User targetUser = getUserById(adminUser.getUserId());
        if (targetUser == null) {
            return "User not found.";
        }

        targetUser.addCoupons(adminUser.getCouponCodes(), adminUser.getCouponValues());
        return "Coupon applied successfully.";
    }


    public User getUserById(String userId) {
        for (User user : newUser) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    public ArrayList<String> getUserTransactions(String userId) {
        for (User user : newUser) {
            if (user.getUserId().equals(userId)) {
                return user.getTransactions();
            }
        }
        return new ArrayList<>();
    }



}
