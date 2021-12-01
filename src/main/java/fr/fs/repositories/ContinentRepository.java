package fr.fs.repositories;

import fr.fs.dto.ContinentDto;
import fr.fs.entities.Continent;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContinentRepository implements PanacheRepositoryBase <Continent, Integer> {

    public List<ContinentDto> findAllContinents()
    {
        return ContinentDto.toContinentDtoList(listAll());
    }
}
