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
public class Allergene {
  /** L'identifiant de l'allergene */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // -- Association -- //

  /** Les produits contenus dans l'allergene */
  @ManyToMany
  @JoinTable(name = "produit_allergene", joinColumns = @JoinColumn(name = "fk_allergene"), inverseJoinColumns = @JoinColumn(name = "fk_produit"))
  private List<Produit> produits;

  // -- Attributs -- //

  /** Le nom de l'allergene */
  @Column(nullable = false, unique = true)
  private String libelle;

  /** Constructeur par défaut */
  public Allergene() {
  }

  /** Retourne l'identifiant */
  public Long getId() {
    return id;
  }

  /** Définit l'identifiant */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Retourne le libelle de l'allergene
   * 
   * @return String
   */
  public String getLibelle() {
    return libelle;
  }

  /**
   * Définit le libelle
   * 
   * @param libelle
   */
  public void setLibelle(String libelle) {
    this.libelle = libelle;
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
   * Définit la liste des produits
   * 
   * @param produits
   */
  public void setProduits(List<Produit> produits) {
    this.produits = produits;
  }

  /**
   * Retourne la chaine de caractères de l'objet
   * 
   * @return String
   */
  @Override
  public String toString() {
    return "Allergene [id=" + id + ", libelle=" + libelle + "]";
  }

}
