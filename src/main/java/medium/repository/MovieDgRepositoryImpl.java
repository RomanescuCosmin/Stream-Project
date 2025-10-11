package medium.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import medium.model.Movie;
import medium.model.MovieDG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class MovieDgRepositoryImpl implements  MovieDgRepository{

    private final ObjectMapper mapper;
    private List<MovieDG> movieDgList = List.of();

    @Autowired
    public MovieDgRepositoryImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    void init() {
        try (InputStream inputStream = new ClassPathResource("data/moviesdg.json").getInputStream()) {
            List<MovieDG> loaded = mapper.readValue(inputStream, new TypeReference<List<MovieDG>>() {
            });
            this.movieDgList = List.copyOf(loaded);
        } catch (Exception e) {
            throw new IllegalStateException("Nu pot incarca data movie/json", e);
        }
    }

    @Override
    public List<MovieDG> findAll() {
        return movieDgList;
    }
}
