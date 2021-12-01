package fr.fs.repositories;

import fr.fs.dto.CountryDto;
import fr.fs.entities.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CountryRepository implements PanacheRepositoryBase <Country, Integer> {

    public List<CountryDto> findAllCountries()
    {
        return CountryDto.toCountryDtoList(listAll());
    }
}
