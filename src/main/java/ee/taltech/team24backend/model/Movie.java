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
    private String genres;

    public Movie(String name, String description, String producer, float rating, String imgUrl, String genres) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.rating = rating;
        this.imgUrl = imgUrl;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie id : " + id +
                ", name : " + name +
                ", description : " + description +
                ", producer : " + producer +
                ", rating : " + rating +
                ", imgUrl : " + imgUrl +
                ", genres : " + genres;
    }

}


