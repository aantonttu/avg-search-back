# Team 24 (AVG) backend
Developers : Anton Antonov, Vladislav Poljakov, German Hanmamedov

# Setup guide
- Clone master branch
- Gradle will automatically configure project
- If there are errors (red text) on getters and setters, so you need to press Ctrl+Shift+A in IntelliJ IDEA, write plugins and click on it, then select Marketplace tab and download Lombok plugin, then in pop-up message click Enable on Annotation Processing.

## Setup note
- Our backend uses external API to get films from https://rapidapi.com/apidojo/api/imdb8
- Unfortunately there are only 500 request/month per 1 account token for free.
- If existing token is out of requests, please register on rapidapi web and change token in src/main/java/ee/taltech/team24backend/MoviesApplicationInit.java class in getMoviesImdbApi() and getMovieDetailApi() functions.

### Main URL
- Movies : http://localhost:8080/api/movies/
- Comments : http://localhost:8080/api/comments/
- SWAGGER : http://localhost:8080/api/swagger-ui/
- H2 console : http://localhost:8080/api/h2-console/

#### Movies URL properties 
- find by id http://localhost:8080/api/movies/{id}
- get sorted movies http://localhost:8080/api/movies/sorted?by={ name, rating, added, year }&order={ asc, desc }
- get sorted movies by genre (not case sensitive) http://localhost:8080/api/movies/sorted?genre={ genre_name }
- find movie by name (not case sensitive) http://localhost:8080/api/movies/find?name={ movie_title }
- get all currently added genres http://localhost:8080/api/movies/genres

#### Comment URL properties
- POST comment http://localhost:8080/api/comments/{ movie_id }  
RequestBody:  
```sh
{
  "commentText": "string",
  "userName": "string"
}
```
- DELETE comment http://localhost:8080/api/comments/{ comment_id }  

