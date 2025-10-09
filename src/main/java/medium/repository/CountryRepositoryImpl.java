package medium.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import medium.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class CountryRepositoryImpl implements CountryRespository {

    private final ObjectMapper objectMapper;
    private List<Country> countries = List.of();

    @Autowired
    public CountryRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @PostConstruct
    void init() {
        try (InputStream inputStream = new ClassPathResource("data/country.json").getInputStream()) {
        List<Country> loaded = objectMapper.readValue(inputStream, new TypeReference<List<Country>>() {});
        this.countries = loaded;
        } catch (Exception e) {
                throw new IllegalArgumentException("Nu pot incarca data country/json");
        }
    }


    @Override
    public List<Country> findAll() {
        return countries;
    }
}
