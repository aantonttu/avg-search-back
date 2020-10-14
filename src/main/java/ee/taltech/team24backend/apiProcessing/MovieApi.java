package ee.taltech.team24backend.apiProcessing;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ee.taltech.team24backend.apiProcessing.Title;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieApi {

//    @JsonProperty("id")
//    private String id;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("ratings")
    private Ratings ratings;
    @JsonProperty("genres")
    private List<String> genres;
//    @JsonProperty("releaseDate")
//    private String releaseDate;
    @JsonProperty("plotSummary")
    private PlotSummary plotSummary;
    @JsonProperty("plotOutline")
    private PlotOutline plotOutline;

}
