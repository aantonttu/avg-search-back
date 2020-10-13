package ee.taltech.team24backend.apiProcessing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {


    @JsonProperty("id")
    private String id;

    @JsonProperty("image")
    private Image image;
    @JsonProperty("runningTimeInMinutes")
    private BigDecimal runningTimeInMinutes;
    @JsonProperty("nextEpisode")
    private String nextEpisode;
    @JsonProperty("numberOfEpisodes")
    private BigDecimal numberOfEpisodes;
    @JsonProperty("seriesEndYear")
    private BigDecimal seriesEndYear;
    @JsonProperty("seriesStartYear")
    private BigDecimal seriesStartYear;
    @JsonProperty("title")
    private String title;
    @JsonProperty("titleType")
    private String titleType;
    @JsonProperty("year")
    private Integer year;

}
