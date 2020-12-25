package ee.taltech.team24backend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ee.taltech.team24backend.apiProcessing.MovieApi;
import ee.taltech.team24backend.apiProcessing.MovieId;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoviesApplicationInit implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> movieTitles = movieRepository.findAll().stream().map(Movie::getName).collect(Collectors.toList());
        List<Movie> movies = new ArrayList<>();
//        List<MovieApi> movieApis = getMoviesImdbApi();
//        for (MovieApi movieApi : movieApis) {
//            if (!movieTitles.contains(movieApi.getTitle().getTitle())) {
//                Movie movie = new Movie(
//                        movieApi.getTitle().getTitle(), movieApi.getPlotOutline().getText(), movieApi.getPlotOutline().getAuthor(),
//                        movieApi.getRatings().getRating(), movieApi.getTitle().getImage().getUrl(), movieApi.getGenres().get(0),
//                        movieApi.getTitle().getYear(), movieApi.getTitle().getRunningTimeInMinutes().intValue()
//                );
//                movies.add(movie);
//            }
//        }
        List<Movie> moviesForTesting = List.of(
                new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "Frank Darabont", 9.3, "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg", "Drama", 1994, 144),
                new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "Francis Ford Coppola", 9.2, "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,704,1000_AL_.jpg", "Drama", 1972, 175),
                new Movie("The Godfather: Part II", "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.", "Francis Ford Coppola", 9.0, "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,701,1000_AL_.jpg", "Drama", 1974, 202),
                new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "Christopher Nolan", 9.0, "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Action", 2008, 152),
                new Movie("Tenet", "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.", "Christopher Nolan", 7.8, "https://m.media-amazon.com/images/M/MV5BYzg0NGM2NjAtNmIxOC00MDJmLTg5ZmYtYzM0MTE4NWE2NzlhXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_SY1000_CR0,0,666,1000_AL_.jpg", "Action", 2020, 150),
                new Movie("The Devil All the Time", "Sinister characters converge around a young man devoted to protecting those he loves in a postwar backwoods town teeming with corruption and brutality.", "Antonio Campos", 7.1, "https://m.media-amazon.com/images/M/MV5BZmE1NmVmN2EtMjZmZC00YzAyLWE4MWEtYjY5YmExMjUxODU1XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Thriller", 2020, 138),
                new Movie("The Witches", "Based on Roald Dahl's 1983 classic book 'The Witches', the story tells the scary, funny and imaginative tale of a seven year old boy who has a run in with some real life witches!", "Robert Zemeckis", 6.8, "https://m.media-amazon.com/images/M/MV5BNjRkYjlhMjEtYzIwOC00ZWYzLTgyMmQtYjI5M2UzNDJkNTU2XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Comedy", 2020, 107),
                new Movie("Antebellum", "Successful author Veronica Henley finds herself trapped in a horrifying reality and must uncover the mind-bending mystery before it's too late.", "Gerard Bush", 5.5, "https://m.media-amazon.com/images/M/MV5BOTQzYWU3NzktOGQyOC00NGNiLWE3NjAtNzQ0YzRkOTc5ODUzXkEyXkFqcGdeQXVyMjMxOTE0ODA@._V1_SY1000_SX675_AL_.jpg", "Horror", 2020, 105),
                new Movie("Joker", "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.", "Todd Phillips", 8.5, "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Drama", 2019, 122),
                new Movie("Harry Potter and the Sorcerer's Stone", "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.", "Chris Columbus", 7.6, "https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_.jpg", "Fantasy", 2001, 152),
                new Movie("Avengers: Endgame", "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", "Joe Russo", 8.4, "https://m.media-amazon.com/images/M/MV5BMjMxNjY2MDU1OV5BMl5BanBnXkFtZTgwNzY1MTUwNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Action", 2019, 149),
                new Movie("Parasite", "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.", "Bong Joon Ho", 8.6, "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Thriller", 2019, 132),
                new Movie("The Outpost", "A small team of U.S. soldiers battle against hundreds of Taliban fighters in Afghanistan.", "Rod Lurie", 6.7, "https://m.media-amazon.com/images/M/MV5BNWYyMzNjY2EtODVmYi00ODBmLWIyNGMtNDdhMGViY2RhNjcxXkEyXkFqcGdeQXVyNDExMzMxNjE@._V1_SY1000_CR0,0,679,1000_AL_.jpg", "Action", 2020, 123),
                new Movie("Once Upon a Time... in Hollywood", "A faded television actor and his stunt double strive to achieve fame and success in the final years of Hollywood's Golden Age in 1969 Los Angeles.", "Quentin Tarantino", 7.6, "https://m.media-amazon.com/images/M/MV5BOTg4ZTNkZmUtMzNlZi00YmFjLTk1MmUtNWQwNTM0YjcyNTNkXkEyXkFqcGdeQXVyNjg2NjQwMDQ@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Comedy", 2019, 161),
                new Movie("Interstellar", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.", "Christopher Nolan", 8.6, "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SY1000_SX675_AL_.jpg", "Drama", 2014, 169),
                new Movie("Black Panther", "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past.", "Ryan Coogler", 7.3, "https://m.media-amazon.com/images/M/MV5BMTg1MTY2MjYzNV5BMl5BanBnXkFtZTgwMTc4NTMwNDI@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Action", 2018, 134),
                new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "Christopher Nolan", 8.8, "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Action", 2010, 148),
                new Movie("Doctor Sleep", "Years following the events of The Shining (1980), a now-adult Dan Torrance must protect a young girl with similar powers from a cult known as The True Knot, who prey on children with powers to remain immortal.", "Mike Flanagan", 7.4, "https://m.media-amazon.com/images/M/MV5BYmY3NGJlYTItYmQ4OS00ZTEwLWIzODItMjMzNWU2MDE0NjZhXkEyXkFqcGdeQXVyMzQzMDA3MTI@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Horror", 2019, 152),
                new Movie("The Addams Family", "The eccentrically macabre family moves to a bland suburb where Wednesday Addams' friendship with the daughter of a hostile and conformist local reality show host exacerbates conflict between the families.", "Conrad Vernon", 5.8, "https://m.media-amazon.com/images/M/MV5BODBjOTAzZmMtNGJkOC00M2M3LWI1MTctZjZlMzdiODBkMzc0XkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Comedy", 2019, 86),
                new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", "Martin Scorsese", 8.2, "https://m.media-amazon.com/images/M/MV5BMjIxMjgxNTk0MF5BMl5BanBnXkFtZTgwNjIyOTg2MDE@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Drama", 2013, 180)
        );
        for (Movie movie : moviesForTesting) {
            if (!movieTitles.contains(movie.getName())) {
                movies.add(movie);
            }
        }

        movieRepository.saveAll(movies);
    }


    public List<MovieApi> getMoviesImdbApi() throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-top-rated-movies")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "3af61540f5msh50e835de0c4278dp1828f8jsneb3d0c462782")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        MovieId[] idArray = objectMapper.readValue(jsonCarArray, MovieId[].class);
        idArray = Arrays.copyOfRange(idArray, 0, 3);
        List<MovieApi> moviesApi = new ArrayList<>();
        for (MovieId movieId : idArray) {
            String[] id = movieId.getId().split("/");
            moviesApi.add(this.getMovieDetailApi(id[id.length - 1]));
        }
        return moviesApi;
    }

    public MovieApi getMovieDetailApi(String movieId) throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-overview-details?tconst=" + movieId)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "3af61540f5msh50e835de0c4278dp1828f8jsneb3d0c462782")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        return objectMapper.readValue(jsonCarArray, MovieApi.class);
    }


}
