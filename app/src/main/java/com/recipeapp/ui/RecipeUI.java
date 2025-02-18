package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.IDataHandlerable;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    // CSVDataHandlerとJSONDataHandlerの両方を受け取れるようにIDataHandlerableに変更
    private IDataHandlerable dataHandler;
    // 変更
    public RecipeUI(IDataHandlerable dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    // メソッドに飛ぶ
                    displayRecipes();
                        break;
                    case "2":
                    addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            // IDataHandlerableからデータの読み込み
            ArrayList<Recipe> recipes = dataHandler.readData();
            System.out.println("Recipes:");

            if (recipes == null|| recipes.isEmpty()) {
                System.out.println("No recipes available.");
            } else {
                for (Recipe recipe : recipes) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + recipe.getName());

                    // カンマ区切り表示
                    ArrayList<Ingredient> ingredients = recipe.getIngredients();
                    ArrayList<String>  ingredientNames = new ArrayList<>();
                    for (Ingredient ingredient : ingredients) {
                        ingredientNames.add(ingredient.getName());
                    }

                    String ingredientsString = String.join(", ", ingredientNames);

                    System.out.println("Main Ingredients: " + ingredientsString);
                }
            }
        } catch(IOException e) {
            System.out.println("e.getMessage()");
        }

    }

    private void addNewRecipe() {
        // 追記
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.println("Enter ingredients (type 'done' when finished): ");
            // Ingredient型のArrayListに格納。可変。
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientsName = reader.readLine();
                if (ingredientsName.equals("done")) {
                    break;
                }
            ingredients.add(new Ingredient(ingredientsName));
            }
            Recipe newRecipe = new Recipe(recipeName, ingredients);

            dataHandler.writeData(newRecipe);

            System.out.println("Recipe added successfully.");

        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}