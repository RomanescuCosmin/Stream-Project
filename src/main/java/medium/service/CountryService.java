package medium.service;


import medium.comparation.ContinentCityPair;
import medium.model.Country;
import medium.repository.CountryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRespository countryRespository;

    public Map<String, Optional<ContinentCityPair>> findMostPopulatedCityOfEachContinent() {
        List<Country> countryList = countryRespository.findAll();
        Map<String, Optional<ContinentCityPair>> collect = countryList
                .stream()
                .map(country -> country.getCities().stream().map(city -> new ContinentCityPair(country.getContinent(), city)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(country -> country.continent(), Collectors.maxBy(ContinentCityPair::compareTo)));
         collect.forEach(ContinentCityPair::printEntry);

         return collect;
    }
}
