package ee.taltech.team24backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MOVIE", schema = "SCHEMA1")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRODUCER")
    private String producer;

    @Column(name = "RATING")
    private double rating;

    @Column(name = "IMG_URL")
    private String imgUrl;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DURATION")
    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    List<Comment> comments;

    public Movie(String name, String description, String producer, double rating, String imgUrl, String genre, Integer year, Integer duration) {
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
        return "MovieApi id : " + id +
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


