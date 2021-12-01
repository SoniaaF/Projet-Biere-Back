package fr.fs.repositories;

import fr.fs.entities.TypeBiere;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TypeBiereRepository implements PanacheRepositoryBase <TypeBiere, Integer> {
}
