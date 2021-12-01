package fr.fs.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Table(name = "CONTINENT")
@Entity
public class Continent extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTINENT", nullable = false)
    private Integer id;

    @Column(name = "NOM_CONTINENT", nullable = false, length = 25)
    private String nomContinent;

    public String getNomContinent() {
        return nomContinent;
    }

    public void setNomContinent(String nomContinent) {
        this.nomContinent = nomContinent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "continent")
    private List<Country> countries;



}