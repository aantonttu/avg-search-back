# Team 24 backend

### Main URL
- Movies : http://localhost:8080/api/movies/
- Comments : http://localhost:8080/api/comments/
- SWAGGER : http://localhost:8080/api/swagger-ui/
- H2 console : http://localhost:8080/api/h2-console/

#### Movies URL properties 
- find by id http://localhost:8080/api/movies/{id}
- get sorted movies http://localhost:8080/api/movies/sorted?by={ name, rating, added, year }&order={ asc, desc }
- find movie by name (not case sensitive) http://localhost:8080/api/movies/find?name={ movie_title }
- get all currently added genres http://localhost:8080/api/movies/allGenres
- find movies with selected genre (not case sensitive) http://localhost:8080/api/movies/genres?genre={ genre_name }

#### Comment URL properties
- find comments for selected movie http://localhost:8080/api/comments/find?movieId={ movie_id }
- POST comment http://localhost:8080/api/comments/{ movie_id } 
RequestBody:
```sh
{
  "commentText": "string",
  "userName": "string"
}
```

