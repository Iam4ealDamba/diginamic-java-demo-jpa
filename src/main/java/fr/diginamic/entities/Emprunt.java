package fr.diginamic.entities;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprunt")
public class Emprunt {
  /** L'id de l'emprunt */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /** Le client de l'emprunt */
  @ManyToOne(targetEntity = Client.class)
  @JoinColumn(name = "id_client", nullable = false)
  private Client client;

  /** Les livres de l'emprunt */
  @ManyToMany
  @JoinTable(name = "compo", joinColumns = @JoinColumn(name = "id_emprunt", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_livre", referencedColumnName = "id"))
  private Set<Livre> livre;

  /** Date de début */
  @Column(name = "datedebut", nullable = false)
  private String dateDebut;

  /** Délai maximum de l'emprunt */
  @Column(name = "delaimax", nullable = true)
  private Integer delaiMax = null;

  /** Date de fin */
  @Column(name = "datefin", nullable = true)
  private String dateFin = null;

  /** Constructeur vide */
  public Emprunt() {
  }

  /**
   * Constructeur avec id,
   * 
   * @param id
   * @param dateDebut
   * @param delaiMax
   * @param dateFin
   */
  public Emprunt(int id, Date dateDebut, Date delaiMax, Date dateFin) {
    this.id = id;
    this.dateDebut = dateDebut;
    this.delaiMax = delaiMax;
    this.delaiMax = dateFin;
  }

  @Override
  public String toString() {
    return "Emprunt [id=" + id + ", dateDebut=" + dateDebut + ", delaiMax="
        + delaiMax + ", dateFin=" + dateFin + "]";
  }

}
