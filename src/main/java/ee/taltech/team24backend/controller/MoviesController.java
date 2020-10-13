package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movies")
@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.findAll();
    }


    @GetMapping("imdb/films")
    public List<MovieApi> getMoviesImdb() throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-top-rated-movies")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .asJson()
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        MovieId[] idArray = objectMapper.readValue(jsonCarArray, MovieId[].class);
        idArray = Arrays.copyOfRange(idArray, 0, 3);
        List<MovieApi> moviesApi = new ArrayList<>();
        for(MovieId movieId : idArray) {
            String[] id = movieId.getId().split("/");
            moviesApi.add(this.getMovieDetailApi(id[id.length - 1]));
        }

        return moviesApi;

    }


    @GetMapping("imdb/details")
    public MovieApi getMovieDetailApi(String movieId) throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-overview-details?tconst=" + movieId)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        return objectMapper.readValue(jsonCarArray, MovieApi.class);

    }

    @GetMapping("imdb/user-reviews")
    public String getMoviesImdbReviews() throws IOException, UnirestException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/title/get-user-reviews?tconst=tt0944947")
                .get()
                .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @GetMapping("find")
    public List<MovieDto> getMoviesByName(@RequestParam(value = "name") String name) {
        return movieService.findByName(name);
    }

    @GetMapping("sorted")
    public List<MovieDto> sortMovies(@RequestParam(value = "by", defaultValue = "name") String by,
                                  @RequestParam(value = "order", defaultValue = "asc") String order) {
        return movieService.sorting(by, order);
    }

    @GetMapping("genres")
    public List<MovieDto> getMoviesByGenres(@RequestParam(value = "genre") String genre) {
        return movieService.getMoviesByGenres(genre);
    }

    @GetMapping("allGenres")
    public List<String> getAllGenres(){
        return movieService.getAllGenres();
    }

    @PostMapping
    public MovieDto saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("{id}")
    public void editMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        movieService.edit(newMovie, id);
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }
}
