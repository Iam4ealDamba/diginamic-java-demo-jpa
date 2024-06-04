package fr.diginamic.entities;

import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity
public class ClientCompte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private Integer idClient;

  @Column
  private Integer idCompte;

  public ClientCompte() {
  }

  public ClientCompte(Integer idClient, Integer idCompte) {
    this.idClient = idClient;
    this.idCompte = idCompte;
  }

}
