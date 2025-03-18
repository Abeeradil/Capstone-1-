package org.example.capstone1.Service;

import org.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> newCategory = new ArrayList<>();

    public ArrayList getCategory (){
       return newCategory;
    }

    public void addCategory(Category category){
        newCategory.add(category);
    }

    public boolean updateCategory (String id, Category category){
        for (Category cat : newCategory){
            if (cat.getId().equalsIgnoreCase(id)){
                newCategory.set(newCategory.indexOf(cat),category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for (Category del : newCategory){
            if (del.getId().equalsIgnoreCase(id)){
                newCategory.remove(del);
                return true;
            }
        }
        return false;
    }


}
