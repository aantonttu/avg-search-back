package ee.taltech.team24backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "COMMENT", schema = "SCHEMA1")

public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOVIE_ID")
    private Long movieId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "TEXT")
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @JsonIgnore
    private Movie movie;

    public Comment(Long movieId, String userName, String commentText) {
        this.userName = userName;
        this.commentText = commentText;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Comment id : " + id +
                ", filmId : " + movieId +
                ", name : " + userName +
                ", commentText : " + commentText;
    }
}
