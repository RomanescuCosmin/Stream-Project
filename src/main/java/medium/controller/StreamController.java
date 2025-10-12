package medium.controller;

import medium.comparation.ContinentCityPair;
import medium.model.Director;
import medium.model.Genre;
import medium.service.CountryService;
import medium.service.MovieDgService;
import medium.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieDgService movieDgService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public String view() {
        return "index.html";
    }

    @ResponseBody
    @GetMapping(value = "/exercice/q1", produces = "application/json")
    public Map<String, Long> getExercice1() {
        return movieService.countMoviesPerDirector();
    }

    @ResponseBody
    @GetMapping(value = "/exercice/q2", produces = "application/json")
    public  Map<String, Optional<ContinentCityPair>> getExercice2() {
        return countryService.findMostPopulatedCityOfEachContinent();
    }

    @ResponseBody
    @GetMapping(value = "/exercice/q3", produces = "application/json")
    public  Map<String, Set<String>> getExercice3() {
        return movieService.getUniqueGenresPerDirector();
    }

    @ResponseBody
    @GetMapping(value = "/exercice/q3-different", produces = "application/json")
    public Map<Director, Map<Genre, Long>> getExercice3_1() {
        return movieDgService.getNumberOfGenresOfEachDirector();
    }
}
