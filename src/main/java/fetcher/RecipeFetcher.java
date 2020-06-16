package fetcher;

import dto.RecipeDTO;
import errorhandling.NoIdExistsException;
import facades.FetchFacade;
import java.io.IOException;

/**
 *
 * @author rando
 */
public class RecipeFetcher {

    private Long id;
    private RecipeDTO RecipeDTO;
    private boolean isCalled = false;

    public RecipeFetcher(Long id) {
        this.id = id;
    }

    public void doWork() throws IOException, NoIdExistsException {
        if (isCalled) {
            return; //Tag values allready set
        }
        isCalled = true;
        RecipeDTO = FetchFacade.getFetchFacade().getRecepieByIdSimple(id);
    }

    public RecipeDTO getRecipeDTO() {
        return RecipeDTO;
    }

}
