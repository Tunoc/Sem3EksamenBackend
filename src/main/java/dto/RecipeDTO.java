package dto;

import java.util.List;

/**
 *
 * @author rando
 */
public class RecipeDTO {

    private String id;
    private String name;
    private String preparation_time;
    private String category;
    private List<String> directions;
    private List<String> ingredient_list;

    public RecipeDTO(String name, String preparation_time, String category) {
        this.name = name;
        this.preparation_time = preparation_time;
        this.category = category;
    }
    
    public RecipeDTO(String id, String name, String preparation_time, String category, List<String> directions, List<String> ingredient_list) {
        this.id = id;
        this.name = name;
        this.preparation_time = preparation_time;
        this.category = category;
        this.directions = directions;
        this.ingredient_list = ingredient_list;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getDirections() {
        return directions;
    }

    public List<String> getIngredient_list() {
        return ingredient_list;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" + "id=" + id + ", name=" + name + ", preparation_time=" + preparation_time + ", category=" + category + ", directions=" + directions + ", ingredient_list=" + ingredient_list + '}';
    }

}
