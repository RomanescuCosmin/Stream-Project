package medium.comparation;

import medium.model.Director;
import medium.model.Genre;

import java.util.List;

public record DirectorGenresPair(Director director, List<Genre> genreList) {
}
