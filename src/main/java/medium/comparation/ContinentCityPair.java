package medium.comparation;

import medium.model.City;

import java.util.Optional;

public record ContinentCityPair(String continent, City city) implements Comparable<ContinentCityPair> {

    @Override
    public int compareTo(ContinentCityPair o) {
        return this.city.getPopulation()-o.city.getPopulation();
    }

    public static void printEntry(String continent, Optional<ContinentCityPair> continentCityPair) {
        continentCityPair.ifPresent(pair -> System.out.printf("%s: %s\n",continent,pair.city()));
    }
}
