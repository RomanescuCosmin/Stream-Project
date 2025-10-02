package medium.service;

import medium.model.Movie;
import medium.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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


}
