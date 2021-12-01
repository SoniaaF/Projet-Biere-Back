package fr.fs.repositories;

import fr.fs.entities.Couleur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CouleurRepository implements PanacheRepositoryBase <Couleur, Integer> {
}
