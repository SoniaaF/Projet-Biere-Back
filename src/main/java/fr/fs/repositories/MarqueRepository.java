package fr.fs.repositories;

import fr.fs.entities.Marque;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarqueRepository implements PanacheRepositoryBase <Marque, Integer> {
}
