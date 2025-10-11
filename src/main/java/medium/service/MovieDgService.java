package medium.service;

import medium.comparation.DirectorGenrePair;
import medium.comparation.DirectorGenresPair;
import medium.model.Director;
import medium.model.Genre;
import medium.model.MovieDG;
import medium.repository.MovieDgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieDgService {

    @Autowired
    private MovieDgRepository movieDgRepository;

    public Map<Director, Map<Genre, Long>> getNumberOfGenresOfEachDirector() {
        List<MovieDG> movieDGList = movieDgRepository.findAll();

        Map<Director, Map<Genre, Long>> collect = movieDGList
                .stream()
                .map(movie -> movie.getDirectors().stream().map(director -> new DirectorGenresPair(director, movie.getGenres())).toList()).
                flatMap(Collection::stream)
                .map(directorGenres -> directorGenres.genreList().stream().map(genre -> new DirectorGenrePair(directorGenres.director(), genre)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(DirectorGenrePair::director, Collectors.groupingBy(DirectorGenrePair::genre, Collectors.counting())));

        collect.forEach((director, genreCounts) -> {
            System.out.printf("%s\n", director.getName());
            genreCounts.forEach((genre, count) -> System.out.printf("\t%s: %s\n", genre.getName(), count));
        });

        return collect;
    }

    public static void main(String[] args) {
        MovieDgService movieDgService = new MovieDgService();
        movieDgService.getNumberOfGenresOfEachDirector();
    }

}
