package ee.taltech.team24backend.model;

import lombok.*;

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

    public Movie(String name, String description, String producer, float rating) {
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie id : " + id +
                ", name : " + name +
                ", description : " + description +
                ", producer : " + producer +
                ", rating : " + rating;
    }

}


