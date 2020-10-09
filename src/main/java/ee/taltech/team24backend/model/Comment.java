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
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private Long filmId;
    private String userName;
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
