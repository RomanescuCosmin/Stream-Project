package medium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private int year;
    private String title;
    private String director;
    private List<String> genres;

    public Movie() {

    }

    public Movie(String title, int year, String director, List<String> genres) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genres = genres;
    }
}
