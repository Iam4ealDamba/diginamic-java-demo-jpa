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
import jakarta.persistence.ManyToOne;

@Entity
public class Produit {
  /** Identifiant du produit */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // -- Association -- //

  /** Liste des ingrédients associe au produit */
  @ManyToMany
  @JoinTable(name = "produit_ingredient", joinColumns = @JoinColumn(name = "fk_produit"), inverseJoinColumns = @JoinColumn(name = "fk_ingredient"))
  private List<Ingredient> ingredients;

  /** Liste des allergènes associe au produit */
  @ManyToMany
  @JoinTable(name = "produit_allergene", joinColumns = @JoinColumn(name = "fk_produit"), inverseJoinColumns = @JoinColumn(name = "fk_allergene"))
  private List<Allergene> allergenes;

  /** Catégorie du produit */
  @ManyToOne
  @JoinColumn(name = "fk_categorie", referencedColumnName = "id")
  private Categorie categorie;

  /** Marque du produit */
  @ManyToOne
  @JoinColumn(name = "fk_marque", referencedColumnName = "id")
  private Marque marque;

  // -- Attributs --

  /** Nom du produit */
  @Column
  private String nom;

  /** Constructeur par défaut */
  public Produit() {
  }

  /** Retourne l'identifiant du produit */
  public Long getId() {
    return id;
  }

  /** Attribut l'identifiant du produit 
   * 
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /** Retourne la liste des ingrédients */
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Attribut la liste des ingrédients
   * 
   * @param ingredients
   */
  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  /** Retourne la liste des allergènes */
  public List<Allergene> getAllergenes() {
    return allergenes;
  }

  /**
   * Attribut la liste des allergènes
   * 
   * @param allergenes
   */
  public void setAllergenes(List<Allergene> allergenes) {
    this.allergenes = allergenes;
  }

  /** Retourne la catégorie */
  public Categorie getCategorie() {
    return categorie;
  }

  /**
   * Attribut la catégorie
   * 
   * @param categorie
   */
  public void setCategorie(Categorie categorie) {
    this.categorie = categorie;
  }

  /** Retourne la marque */
  public Marque getMarque() {
    return marque;
  }

  /**
   * Attribut la marquemarque
   * 
   * @param marque
   */
  public void setMarque(Marque marque) {
    this.marque = marque;
  }

  /** Retourne le nom */
  public String getNom() {
    return nom;
  }

  /**
   * Attribut le nom
   * 
   * @param nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

}
