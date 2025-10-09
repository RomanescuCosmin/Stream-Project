package medium.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;

    public City(int id, String name, int population, String countryCode) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.countryCode= countryCode;
    }

    @Override
    public boolean equals(Object o) {
       if(this == o) return true;
       if(o == null) return false;
       if(getClass() != o.getClass()) return false;
       City other = (City) o;
       if(id != other.id) return false;

       return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", population="
                + population + ", countryCode=" + countryCode + "]";
    };


}
