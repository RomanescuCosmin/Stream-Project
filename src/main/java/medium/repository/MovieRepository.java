package medium.repository;

import medium.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> findAll();
}
