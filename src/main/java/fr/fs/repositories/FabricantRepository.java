package fr.fs.repositories;

import fr.fs.entities.Fabricant;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FabricantRepository implements PanacheRepositoryBase <Fabricant, Integer> {
}
