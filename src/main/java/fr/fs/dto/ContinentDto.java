package fr.fs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.fs.entities.Continent;
import fr.fs.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonPropertyOrder({"id", "nom", "countries"})
public class ContinentDto {
    private Integer id;
    private String nom;
    List<CountryDto> countries;


    public ContinentDto(Continent continent){
        id = continent.getId();
        nom = continent.getNomContinent();
        countries = new ArrayList<>();
        for (Country country: continent.getCountries()) {
            countries.add(new CountryDto(country.getId(), country.getNom()));
        }
    }

    public static List<ContinentDto> toContinentDtoList(List<Continent> continents)
    {
        List<ContinentDto> continentDtoList = new ArrayList<>();
        for (Continent continent : continents)
        {
            continentDtoList.add(new ContinentDto(continent));
        }
        return continentDtoList;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public class CountryDto{
        private Integer id;
        private String nom;
    }

}
