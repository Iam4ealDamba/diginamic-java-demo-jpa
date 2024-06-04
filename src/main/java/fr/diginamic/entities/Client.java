package fr.diginamic.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // --- Foreign ---
  @ManyToMany
  @JoinTable(name = "client_compte", joinColumns = @JoinColumn(name = "fk_client"), inverseJoinColumns = @JoinColumn(name = "fk_compte"))
  private List<Compte> comptes;

  // ----------- Columns -------------

  @Column
  private String nom;

  @Column
  private String prenom;

  @Column
  @Temporal(TemporalType.DATE)
  private LocalDate dateNaissance;

  @Embedded
  private Adresse adresse;

  public Client() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
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

  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  @Override
  public String toString() {
    return "Client [id=" + id + ", comptes=" + comptes + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
        + dateNaissance + ", adresse=" + adresse.toString() + "]";
  }

  public List<Compte> getComptes() {
    return comptes;
  }

  public void setComptes(List<Compte> comptes) {
    this.comptes = comptes;
  }

}
