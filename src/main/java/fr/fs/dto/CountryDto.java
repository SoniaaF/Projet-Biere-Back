package fr.fs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.fs.entities.Country;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"id", "nom", "continent"})
public class CountryDto {

    private Integer id;
    private String nom;

    private ContinentDto continent;

    public CountryDto (Country country)
    {
        id = country.getId();
        nom = country.getNom();
        continent = new ContinentDto();
        continent.setId(country.getContinent().getId());
        continent.setNom(country.getContinent().getNomContinent());
    }

    public static List<CountryDto> toCountryDtoList(List<Country> countries)
    {
        List<CountryDto> countriesDto = new ArrayList<>();
        for (Country country : countries)
            countriesDto.add(new CountryDto(country));

        return countriesDto;
    }


    @Setter
    @Getter
    public class ContinentDto{
        private Integer id;
        private String nom;
    }
}
