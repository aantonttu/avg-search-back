package ee.taltech.team24backend.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String name;
    private String description;
    private String producer;
    private double rating;
    private String imgUrl;
    private String genre;
    private Integer year;
    private Integer duration;
    List<CommentDto> comments;
}
