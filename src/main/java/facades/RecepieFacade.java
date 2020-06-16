package facades;

import dto.RecipeDTO;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rando
 */
public class RecepieFacade {

    private static RecepieFacade instance;
    private static EntityManagerFactory emf;
    private FetchFacade ff = FetchFacade.getFetchFacade();
    
    //Private Constructor to ensure Singleton
    private RecepieFacade() {
    }
    
    public static RecepieFacade getRecepieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecepieFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<RecipeDTO> getAllRecepies() throws IOException {
        List<RecipeDTO> RecepieList = ff.getAllRecipe();
        return RecepieList;
    }
    
    public List<RecipeDTO> getSpecificRecepieInfo(List<Long> idListOfLong) throws InterruptedException {
        List<RecipeDTO> RecepieList = ff.fetchParallel(idListOfLong);
        return RecepieList;
    }
    
}
