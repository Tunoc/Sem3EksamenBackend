package facades;

import com.google.gson.Gson;
import dto.RecipeDTO;
import errorhandling.NoIdExistsException;
import fetcher.RecipeFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import utils.HttpUtils;

/**
 *
 * @author rando
 */
public class FetchFacade {

    private static FetchFacade instance;
    private static final Gson gson = new Gson();

    //Private Constructor to ensure Singleton
    private FetchFacade() {
    }

    public static FetchFacade getFetchFacade() {
        if (instance == null) {
            instance = new FetchFacade();
        }
        return instance;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        List<Long> idList = new ArrayList();
//        idList.add(1L);
//        idList.add(3L);
//        List<RecipeDTO> test = new ArrayList();
//        test.addAll(0, fetchParallel(idList));
//        System.out.println(test.get(0));
//        System.out.println(test.get(1));
//      //Husk at gøre metoderne statiske hvis de skal kunne testes manuelt via main.
//    }

    public List<RecipeDTO> getAllRecipe() throws IOException {
        String fetchedJSONString = HttpUtils.fetchData("https://cphdat.dk/recipes");
        if (fetchedJSONString.contains("error")) {
            //throw new MovieNotFoundException("No movie found with id: " + id);
        }
        //test test = gson.fromJson(fetchedJSONString, test.class);
        List<RecipeDTO> rDTOList = new ArrayList();

        String editedFetchString = fetchedJSONString;

        do {
            int recepieStart = editedFetchString.indexOf("\":{");
            recepieStart += 2;

            String recepieIDs = editedFetchString.substring(editedFetchString.indexOf("\"") + 1);
            recepieIDs = recepieIDs.substring(0, recepieIDs.indexOf("\""));
            Long recipeID = Long.parseLong(recepieIDs);

            int recepieEnd;
            if (! !editedFetchString.contains("},")) {
                recepieEnd = editedFetchString.indexOf("},");
                recepieEnd += 1;
            } else {
                recepieEnd = editedFetchString.length() - 1;
            }

            String recepieDTOString = editedFetchString.substring(recepieStart, recepieEnd);
            RecipeDTO constructRecipeDTO = gson.fromJson(recepieDTOString, RecipeDTO.class);
            constructRecipeDTO.setId(recipeID);
            rDTOList.add(constructRecipeDTO);

            editedFetchString = editedFetchString.substring(recepieEnd, editedFetchString.length());
        } while (editedFetchString.length() > 3);
        //RecipeCollectionDTO rcDTO = new RecipeCollectionDTO(gson.fromJson(fetchedJSONString, RecipeDTO.class));
        //Fix the conversion from recepies to something i can use.
        //String editedString = fetchedJSONString.substring(1, fetchedJSONString.length()-1);
        return rDTOList;
    }

    public List<RecipeDTO> fetchParallel(List<Long> idList) throws InterruptedException {
        List<RecipeFetcher> fetchCalls = new ArrayList<>();
        for (long recepieID : idList) {
            fetchCalls.add(new RecipeFetcher(recepieID));
        }
        ExecutorService workingJack = Executors.newFixedThreadPool(7); //7 because there are 7 days a week.
        for (RecipeFetcher fc : fetchCalls) {
            Runnable task = () -> {
                try {
                    fc.doWork();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            workingJack.submit(task);
        }
        workingJack.shutdown();
        workingJack.awaitTermination(15, TimeUnit.SECONDS);
        List<RecipeDTO> returnList = new ArrayList<>();
        for (RecipeFetcher fc : fetchCalls) {
            returnList.add(fc.getRecipeDTO());
        }
        return returnList;
    }

    public RecipeDTO getRecepieByIdSimple(Long id) throws IOException, NoIdExistsException {
        String fetchedJSONString = HttpUtils.fetchData("https://cphdat.dk/recipe/" + id);
        if (fetchedJSONString.contains("error")) {
            throw new NoIdExistsException();
        }
        RecipeDTO fetchedRecipe = gson.fromJson(fetchedJSONString, RecipeDTO.class);
        return fetchedRecipe;
    }
}
