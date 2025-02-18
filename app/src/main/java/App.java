import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.IDataHandlerable;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;

public class App {
    public static void main(String[] args) {
        // ユーザーからの入力により、CSVDataHandlerまたはJSONDataHandlerのインスタンスを作成
        // RecipeUIに渡してdisplayMenuメソッドを呼びだし、メインメニューを表示する

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            IDataHandlerable dataHandler;

            // 1を選択した場合
            if ("1".equals(choice)) {
                // CSVDataHandlerインスタンスを生成
                dataHandler = new CSVDataHandler();
            // 2を選択した場合
            } else if ("2".equals(choice)) {
                // JSONDataHandlerインスタンスを生成
                dataHandler = new JSONDataHandler();
            // 1,2以外の不正な値を入力した場合
            } else {
                // CSVDataHandlerインスタンスを生成
                dataHandler = new CSVDataHandler();
            }

            RecipeUI recipeUI = new RecipeUI(dataHandler);
            recipeUI.displayMenu();
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}