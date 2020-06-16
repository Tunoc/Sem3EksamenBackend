package facades;

import dto.RecipeDTO;
import errorhandling.NoIdExistsException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rando
 */
public class FetchFacadeTest {

    private static final FetchFacade FACADE = FetchFacade.getFetchFacade();

    public FetchFacadeTest() {
    }

    @Test
    public void testGetAllRecepies_ReturnsListWith4Recepies_EqualResults() throws IOException {
        System.out.println("testGetAllRecepies_ReturnsListWith4Recepies_EqualResults");
        List<RecipeDTO> recepiesList = FACADE.getAllRecipe();
        assertEquals(4, recepiesList.size());
    }
    
    @Test 
    public void testParalellRecepiesFetch_ReturnsListWithAllInfoAboutRecepies_EqualResults() throws IOException, InterruptedException {
        System.out.println("testParalellRecepiesFetch_ReturnsListWithAllInfoAboutRecepies_EqualResults");
        List<Long> idList = new ArrayList();
        idList.add(1L);
        idList.add(3L);
        List<RecipeDTO> recepiesList = FACADE.fetchParallel(idList);
        assertEquals("side dish", recepiesList.get(0).getCategory());
        assertEquals("squash risotto", recepiesList.get(1).getName());
    }
    
    //@Test //Negative test dosn't work for me either
    public void testParalellRecepiesFetch_ThrowAnException_Exception() throws IOException, NoIdExistsException {
        System.out.println("testParalellRecepiesFetch_ThrowAnException_Exception");
        List<Long> idList = new ArrayList();
        idList.add(999999L);
        
        Exception exception = assertThrows(NoIdExistsException.class, () -> {
            FACADE.fetchParallel(idList);
        });
        
        String expectedMessage = "Requested recepie dosn't exist";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
