package fr.diginamic.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {
  /** Identifiant de l'ingrédient */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // -- Association -- //

  /** Liste des produits de l'ingrédient */
  @ManyToMany
  @JoinTable(name = "produit_ingredient", joinColumns = @JoinColumn(name = "fk_ingredient"), inverseJoinColumns = @JoinColumn(name = "fk_produit"))
  private List<Produit> produits;

  // -- Attributs -- //

  /** Libelle de l'ingrédient */
  @Column(nullable = false, unique = true)
  private String libelle;

  /** Constructeur par défaut */
  public Ingredient() {
  }

  /**
   * Retourne l'identifiant de l'ingredient
   * 
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * Attribut l'identifiant de l'ingredient
   * 
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Retourne le libelle de l'ingrédient
   * 
   * @return String
   */
  public String getLibelle() {
    return libelle;
  }

  /**
   * Attribut le libelle de l'ingredient
   * 
   * @param libelle
   */
  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  /**
   * Retourne la liste des produits de l'ingredient
   * 
   * @return List<Produit>
   */
  public List<Produit> getProduits() {
    return produits;
  }

  /**
   * Attribut la liste des produits de l'ingredient
   * 
   * @param produits
   */
  public void setProduits(List<Produit> produits) {
    this.produits = produits;
  }

  /**
   * Retourne la chaine de caractère de l'objet
   * 
   * @return String
   */
  @Override
  public String toString() {
    return "Ingredient [id=" + id + ", libelle=" + libelle + "]";
  }

}
