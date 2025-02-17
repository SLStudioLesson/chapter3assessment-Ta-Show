package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements IDataHandlerable {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() {
        // recipe.csvのデータをリスト型に読み込み、返す
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if ( parts.length > 1) {
                    String recipeName = parts[0];
                    String[] ingredientsList = parts[1].split(",");
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    for (String ingredient : ingredientsList) {
                        ingredients.add(new Ingredient(ingredient));
                        recipes.add(new Recipe(recipeName, ingredients));
                    }
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) {

    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
