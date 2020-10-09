package ee.taltech.team24backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String producer;
    private float rating;
    private String imgUrl;
    private String genre;
    private Integer year;
    private Integer duration;

    public Movie(String name, String description, String producer, float rating, String imgUrl, String genre, Integer year, Integer duration) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie id : " + id +
                ", name : " + name +
                ", description : " + description +
                ", producer : " + producer +
                ", rating : " + rating +
                ", year : " + year +
                ", duration : " + duration +
                ", genre : " + genre +
                ", imgUrl : " + imgUrl;
    }
}


