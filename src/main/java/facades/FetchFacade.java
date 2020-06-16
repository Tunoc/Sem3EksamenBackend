package facades;

import com.google.gson.Gson;
import dto.CompleteDTO;
import dto.RecipeDTO;
import fetcher.ChuckFetcher;
import fetcher.FetcherInterface;
import fetcher.KanyeRestFetcher;
import fetcher.OmdbFetcher;
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

    public static List<RecipeDTO> getAllRecipe() throws IOException {
        String fetchedJSONString = HttpUtils.fetchData("https://cphdat.dk/recipes");
        if (fetchedJSONString.contains("error")) {
            //throw new MovieNotFoundException("No movie found with id: " + id);
        }
        //test test = gson.fromJson(fetchedJSONString, test.class);
        
        //RecipeCollectionDTO rcDTO = new RecipeCollectionDTO(gson.fromJson(fetchedJSONString, RecipeCollectionDTO.class));
        //Fix the conversion from recepies to something i can use.
        
        String editedString = fetchedJSONString.substring(1, fetchedJSONString.length()-1);
        
        
        
        List<RecipeDTO> rDTO = new ArrayList();
        rDTO.add(gson.fromJson(editedString, RecipeDTO.class));
        System.out.println(rDTO);
        return rDTO;
    }
    
    public static void main(String[] args) throws IOException {
        List<RecipeDTO> test = new ArrayList();
        test.addAll(0, getAllRecipe());
        
    }

    public CompleteDTO runParalel() throws InterruptedException {
        OmdbFetcher omdbFetcher = new OmdbFetcher("http://www.omdbapi.com/?t=Game%20Of%20Thrones&Season1&apikey=6b10a5de");
        ChuckFetcher chuckFetcher = new ChuckFetcher("https://api.chucknorris.io/jokes/random");
        KanyeRestFetcher kanyerestFetcher = new KanyeRestFetcher("https://api.kanye.rest/");
        
        //Lav om her så den paralel fetcher recepies baseret på ens recepie ID'er.
        
        List<FetcherInterface> urls = new ArrayList();
        urls.add(omdbFetcher);
        urls.add(chuckFetcher);
        urls.add(kanyerestFetcher);
        ExecutorService workingJack = Executors.newFixedThreadPool(3);
        for (FetcherInterface fetch : urls) {
            Runnable task = () -> {
                try {
                    fetch.doWork();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            workingJack.submit(task);
        }
        workingJack.shutdown();
        workingJack.awaitTermination(15, TimeUnit.SECONDS);
        return new CompleteDTO(omdbFetcher.getOmdbApiDTO(), chuckFetcher.getChuckDTO(), kanyerestFetcher.getKanyeRestDto());
    }

}
