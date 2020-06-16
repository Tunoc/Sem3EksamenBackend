package dto;

/**
 *
 * @author rando
 */
public class IngredientDTO {

    private final int amount;
    private final String name;

    public IngredientDTO(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

}
