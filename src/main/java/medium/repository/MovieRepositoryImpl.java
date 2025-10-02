package medium.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import medium.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final ObjectMapper objectMapper;
    private List<Movie> movies = List.of();

    @Autowired
    public MovieRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void init() {
        try (InputStream inputStream = new ClassPathResource("data/movies.json").getInputStream()) {
            List<Movie> loaded = objectMapper.readValue(inputStream, new TypeReference<List<Movie>>() {
            });
            this.movies = List.copyOf(loaded);
        } catch (Exception e) {
            throw new IllegalStateException("Nu pot incarca data movie/json", e);
        }
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }
}
