package fetcher;

import com.google.gson.Gson;
import dto.RecipeDTO;
import java.io.IOException;
import utils.HttpUtils;

/**
 *
 * @author rando
 */
public class RecipeFetcher implements FetcherInterface{
    
    private String url;
    private RecipeDTO RecipeDTO;

    public RecipeFetcher(String url) {
        this.url = url;
    }
    
    @Override
    public void doWork() throws IOException {
        Gson gson = new Gson();
        String Recipe = HttpUtils.fetchData(url);
        RecipeDTO = gson.fromJson(Recipe, RecipeDTO.class);
    }
    
}
