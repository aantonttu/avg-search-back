# Team 24 (AVG) backend
Developers : Anton Antonov, Vladislav Poljakov, German Hanmamedov  
[Business analysis](https://gitlab.cs.ttu.ee/gehanm/iti0203-2020-team24-back/-/wikis/Business-analysis)  
[User stories](https://gitlab.cs.ttu.ee/gehanm/iti0203-2020-team24-back/-/wikis/User-stories)

# Setup guide
- Clone main branch
- Gradle will automatically configure project
- If there are errors (red text) on getters and setters, so you need to press Ctrl+Shift+A in IntelliJ IDEA, write plugins and click on it, then select Marketplace tab and download Lombok plugin, then in pop-up message click Enable on Annotation Processing.

## Setup note
- Our backend uses external API to get films from https://rapidapi.com/apidojo/api/imdb8
- Unfortunately there are only 500 request/month per 1 account token for free.
- If existing token is out of requests, please register on rapidapi web and change token in ```src/main/java/ee/taltech/team24backend/MoviesApplicationInit.java``` class in ```getMoviesImdbApi()``` and ```getMovieDetailApi()``` functions.

## Amount of movies
To change amount of movies just go to ```MoviesApplicationInit``` and change ```idArray``` copy range (line 56). Should be 0 to how many you want, but more films requires more time.

### Main URL
- Movies : http://localhost:8080/api/movies/
- Comments : http://localhost:8080/api/comments/
- SWAGGER : http://localhost:8080/api/swagger-ui/
- H2 console : http://localhost:8080/api/h2-console/
- AWS : http://13.48.127.84/

#### Movies URL properties 
- find by id http://localhost:8080/api/movies/{id}
- get sorted movies http://localhost:8080/api/movies?by={ name, rating, relevance, year }&order={ asc, desc }
- get sorted movies by genre (not case sensitive) http://localhost:8080/api/movies?genre={ genre_name }
- find movie by name (not case sensitive) http://localhost:8080/api/movies?name={ movie_title }
- get all currently added genres http://localhost:8080/api/movies/genres
- POST comment http://localhost:8080/api/movies/{ movie_id }/comments  
RequestBody:  
```sh
{
  "commentText": "string",
  "userName": "string"
}
```

#### Comment URL properties
- DELETE comment http://localhost:8080/api/comments/{ comment_id }  

#### User URL properties
- Register : http://localhost:8080/api/users/register  
RequestBody:  
```sh
{
  "username": "string",
  "password": "string"
}
```
- Login : http://localhost:8080/api/users/Login  
RequestBody:  
```sh
{
  "username": "string",
  "password": "string"
}
```
- Get logged in user: http://localhost:8080/api/users/me
- DELETE user: http://localhost:8080/api/users/{ user_id }


