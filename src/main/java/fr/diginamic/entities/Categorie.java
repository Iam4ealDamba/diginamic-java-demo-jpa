package fr.diginamic.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categorie {
  /** l'identifiant de la catégorie */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // -- Associations -- //

  /** la liste des produits de la catégorie */
  @OneToMany(mappedBy = "categorie")
  private List<Produit> produits;

  // -- Attributs -- //

  /** le libelle de la catégorie */
  @Column(nullable = false, unique = true)
  private String libelle;

  /** Constructeur par défaut */
  public Categorie() {
  }

  /**
   * Retiurne l'identifiant de la catégorie
   * 
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * Attribut l'identifiant de la catégorie
   * 
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Retourne la liste des produits
   * 
   * @return List<Produit>
   */
  public List<Produit> getProduits() {
    return produits;
  }

  /**
   * Attribut la liste des produits
   * 
   * @param produits
   */
  public void setProduits(List<Produit> produits) {
    this.produits = produits;
  }

  /**
   * Retourne le libelle
   * 
   * @return String
   */
  public String getLibelle() {
    return libelle;
  }

  /**
   * Attribut le libelle
   * 
   * @param libelle
   */
  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  /**
   * Retourne la chaine de caractères de l'objets
   * 
   * @return String
   */
  @Override
  public String toString() {
    return "Categorie [id=" + id + ", produits=" + produits + ", libelle=" + libelle + "]";
  }

}
