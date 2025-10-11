package medium.service;

import medium.model.Movie;
import medium.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public Map<String, Long> countMoviesPerDirector() {
        List<Movie> movieList = repository.findAll();
        Map<String, Long> getDirectorWithMoreMovie = movieList.stream()
                .filter(f -> f.getDirector() != null || f.getDirector() != "")
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        return getDirectorWithMoreMovie;
    }


    public Map<String, Set<String>> getUniqueGenresPerDirector() {
        List<Movie> movieList = repository.findAll();

        return movieList.stream()
                .filter(f -> f.getGenres() != null && !f.getGenres().isEmpty())
                .flatMap(movie -> movie.getGenres().stream()
                        .map(genre -> Map.entry(movie.getDirector(), genre)))
                .distinct()
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
                ));
    }

}
