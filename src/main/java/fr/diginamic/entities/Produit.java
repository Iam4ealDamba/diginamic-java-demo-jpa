package fr.diginamic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produit {
  @Id
  private Long id;

  // -- Relations
  @ManyToOne
  @JoinColumn(name = "fk_categorie", referencedColumnName = "id")
  private Categorie categorie;

  @ManyToOne
  @JoinColumn(name = "fk_marque", referencedColumnName = "id")
  private Marque marque;

  // -- Getters et Setters

  @Column
  private String nom;
}
