package fr.diginamic.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Operation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // --- Foreign ---
  @ManyToOne
  @JoinColumn(name = "fk_compte", nullable = false, referencedColumnName = "id")
  private Compte compte;
  // ----------- Columns -------------

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime date = LocalDateTime.now();

  @Column
  private Double montant;

  @Column
  private String motif;

  public Operation() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Double getMontant() {
    return montant;
  }

  public void setMontant(Double solde) {
    this.montant = solde;
  }

  public Compte getCompte() {
    return compte;
  }

  public void setCompte(Compte compte) {
    this.compte = compte;
  }

  public String getMotif() {
    return motif;
  }

  public void setMotif(String motif) {
    this.motif = motif;
  }

}
