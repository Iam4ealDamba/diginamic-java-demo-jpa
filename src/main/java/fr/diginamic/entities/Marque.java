package fr.diginamic.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Marque {
  /** Identifiant de la marque */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // -- Associations -- //

  /** Liste des produits de la marque */
  @OneToMany(mappedBy = "marque")
  private List<Produit> produits;

  // -- Attributs -- //

  /** Libelle de la marque */
  @Column(nullable = false, unique = true)
  private String libelle;

  /** Constructeur par défaut */
  public Marque() {
  }

  /**
   * Retiurne l'identifiant de la marque
   * 
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * Attribut l'identifiant de la marque
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
   * Attribut la liste des produits de la marque
   * 
   * @param produits
   */
  public void setProduits(List<Produit> produits) {
    this.produits = produits;
  }

  /**
   * Retourne le libelle de la marque
   * 
   * @return String
   */
  public String getLibelle() {
    return libelle;
  }

  /**
   * Attribut le libelle de la marque
   * 
   * @param libelle
   */
  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

}
