package dto;

/**
 *
 * @author rando
 */
public class RecipeCollectionDTO {

    private final RecipeDTO Recipe;

    public RecipeCollectionDTO(RecipeDTO Recipe) {
        this.Recipe = Recipe;
    }

    public RecipeDTO getRecipe() {
        return Recipe;
    }

}
