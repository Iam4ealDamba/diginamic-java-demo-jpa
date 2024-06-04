package fr.diginamic.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {
  /** L'id du livre */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  /** Les emprunts du livre */
  @ManyToMany
  @JoinTable(name = "compo", joinColumns = @JoinColumn(name = "id_livre", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_emprunt", referencedColumnName = "id"))
  private Set<Emprunt> emprunts;

  /** Le titre du livre */
  @Column(name = "title", nullable = false)
  private String title;

  /** L'auteur du livre */
  @Column(name = "auteur", nullable = false)
  private String auteur;

  /** Constructeur vide */
  public Livre() {
  }

  /**
   * Constructeur avec id, titre et auteur
   * 
   * @param id
   * @param title
   * @param auteur
   */
  public Livre(int id, String title, String auteur) {
    this.id = id;
    this.title = title;
    this.auteur = auteur;
  }

  

  @Override
  public String toString() {
    return "Livre [id=" + id + ", emprunts=" + emprunts + ", title=" + title + ", auteur=" + auteur + "]";
  }

  public int getId() {
    return id;
  }

  public Set<Emprunt> getEmprunts() {
    return emprunts;
  }

  public String getTitle() {
    return title;
  }

  public String getAuteur() {
    return auteur;
  }
}
