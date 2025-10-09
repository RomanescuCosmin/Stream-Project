package medium.repository;

import medium.model.Country;

import java.util.List;


public interface CountryRespository {

    List<Country> findAll();
}
