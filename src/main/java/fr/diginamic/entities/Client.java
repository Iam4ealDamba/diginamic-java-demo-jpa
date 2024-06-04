package fr.diginamic.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
  /** L'id du client */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /** Les emprunts du client */
  @OneToMany(mappedBy = "client")
  private Set<Emprunt> emprunts;

  /** Nom du client */
  @Column(name = "nom", nullable = false)
  private String nom;

  /** Prenom du client */
  @Column(name = "prenom", nullable = false)
  private String prenom;

  /** Constructeur vide */
  public Client() {
  }

  /**
   * Constructeur avec id, nom et prenom
   * 
   * @param id
   * @param nom
   * @param prenom
   */
  public Client(int id, String nom, String prenom) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<Emprunt> getEmprunts() {
    return emprunts;
  }

  public void setEmprunts(Set<Emprunt> emprunts) {
    this.emprunts = emprunts;
  }

  public String getNom() {
    return nom.toUpperCase();
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

}
