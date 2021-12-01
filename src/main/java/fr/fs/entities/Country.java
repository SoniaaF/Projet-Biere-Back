package fr.fs.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Table(name = "PAYS")
@Entity
@JsonPropertyOrder({"id","nom","continent"})
public class Country extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAYS", nullable = false)
    private Integer id;

    @Column(name = "NOM_PAYS", nullable = false, length = 40)
    private String nom;



   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name = "ID_CONTINENT", nullable = false)
    private Continent continent;

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent idContinent) {
        this.continent = idContinent;
  }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomPays) {
        this.nom = nomPays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}