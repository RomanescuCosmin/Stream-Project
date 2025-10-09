package medium.controller;

import medium.comparation.ContinentCityPair;
import medium.service.CountryService;
import medium.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    private MovieService movieService;

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
}
