package ee.taltech.team24backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private Long movieId;
    private String userName;
    private String commentText;
}
