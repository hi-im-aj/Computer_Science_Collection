package app;

import component.Recipe;
import component.RecipeData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private List<Recipe> recipeList;

    @FXML
    private ListView<Recipe> recipeListView;
    @FXML
    private TextArea recipeTextArea;

    private TextInputDialog prompt(String header) {
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(header);
        tid.showAndWait();
        return tid;
    }

    public void handleNewRecipe() {
        TextInputDialog tid = prompt("Enter recipe name");
        String name = tid.getEditor().getText();

        tid = prompt("Enter ingredients (separate by comma ',')");
        String[] ingredients = tid.getEditor().getText().split(",");

        tid = prompt("Enter instructions (separate by comma ',')");
        String[] instructionsArray = tid.getEditor().getText().split(",");
        StringBuilder sb = new StringBuilder();
        for (String e : instructionsArray) {
            sb.append(e).append("\n");
        }
        String instructions = sb.substring(0,sb.length() - 1);

        tid = prompt("Enter notes");
        String notes = tid.getEditor().getText();

        recipeList.add(new Recipe(name, ingredients, instructions, notes));
        recipeListView.getItems().setAll(recipeList);
        RecipeData.getInstance().setRecipeItems(recipeList);
    }

    public void handleClickRecipeListView() {
        Recipe recipe = recipeListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder();
        sb.append(recipe.getName()).append("\n\n\nIngredients:\n\n");
        int count = 0;
        // EXAMPLE While loop
        while (count < recipe.getIngredients().length) {
            sb.append(String.format("%s\n", recipe.getIngredients()[count]));
            count++;
        }
        sb.append("\n\nInstructions:\n\n").append(recipe.getInstructions()).append("\n\n\nNotes:\n\n").append(recipe.getNotes());
        recipeTextArea.setText(sb.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // EXAMPLE Array
        Recipe[] recipes = {new Recipe("Te med ingef??r, honning, citron og hvidl??g", new String[]{"1 citron", "3 dl vand",
                "5 cm ingef??r", "1 fed presset hvidl??g"}, "1. Kom en hel citron sk??ret i fire b??de i en gryde fyldt " +
                "med 3 dl kogende vand. Tils??t ca. 5 cm frisk ingef??r. Lad blandingen koge op i 1 minuts tid. Tag " +
                "det af varmen og lad det hvile i 10 min. 1 fed presset hvidl??g blandes i sammen med 1 spsk. honning.",
                "Du kan ogs?? variere teen og tils??tte en knivspids cayenne peber (for at l??sne slim) eller komme andre " +
                        "lindrende krydderier i som f.eks. kanel eller nelliker."),
                new Recipe("Glasur", new String[]{"200 g flormelis", "?? dl kogende vand"}, "1. Bland flormelis og kogende vand sammen i en sk??l.\n" +
                        "2. Bland indtil du har en ensartet glasur.\n" +
                        "3. Pynt din kage med glasuren.", "Kakaoglasur: Skift cirka ??? af flormelisen ud med us??det kakaopulver \n" +
                        "Andre variationer: Du kan skifte vandet ud med frugtsaft ??? f.eks. citronsaft, appelsinsaft el. lign " +
                        "eller du kan skifte vandet ud med f.eks. frugtfarve, s?? glasuren m??ske bliver r??d eller gr??n.")};

        // EXAMPLE ArrayList
        recipeList = new ArrayList<Recipe>();

        // EXAMPLE For loop
        for (int i = 0; i < recipes.length; i++) {
            recipeList.add(recipes[i]);
        }

        recipeListView.getItems().setAll(recipeList);
        recipeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        RecipeData.getInstance().setRecipeItems(recipeList);
    }
}
