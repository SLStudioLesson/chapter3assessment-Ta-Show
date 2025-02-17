package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.IDataHandlerable;
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
            }

            for (Recipe recipe : recipes) {
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + recipe.getName());
                System.out.println("Main Ingredients: " + recipe.getIngredients());
            }
        } catch(IOException e) {
            System.out.println("e.getMessage()");
        }

    }
}
