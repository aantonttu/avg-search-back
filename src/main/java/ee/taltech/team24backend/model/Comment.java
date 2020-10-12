package ee.taltech.team24backend.model;

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
    private Long filmId;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "TEXT")
    private String commentText;

    public Comment(Long filmId, String userName, String commentText) {
        this.userName = userName;
        this.commentText = commentText;
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "Comment id : " + id +
                ", filmId : " + filmId +
                ", name : " + userName +
                ", commentText : " + commentText;
    }
}
