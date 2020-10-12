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
    private Long movieId;
    private String userName;
    private String commentText;

    public Comment(Long movieId, String userName, String commentText) {
        this.userName = userName;
        this.commentText = commentText;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Comment id : " + id +
                ", movieId : " + movieId +
                ", name : " + userName +
                ", commentText : " + commentText;
    }
}
