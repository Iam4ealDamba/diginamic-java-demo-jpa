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
import jakarta.persistence.OneToMany;

@Entity
public class Compte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  // --- Foreign ---
  @OneToMany(mappedBy = "compte")
  private List<Operation> operations;

  @ManyToOne
  @JoinColumn(name = "fk_banque", nullable = false, referencedColumnName = "id")
  private Banque banque;

  @ManyToMany
  @JoinTable(name = "client_compte", joinColumns = @JoinColumn(name = "fk_compte"), inverseJoinColumns = @JoinColumn(name = "fk_client"))
  private List<Client> clients;

  // ----------- Columns -------------

  @Column
  private String numero;

  @Column
  private Double solde;

  public Compte() {
  }

  public Compte(String numero, Double solde) {
    this.numero = numero;
    this.solde = solde;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Double getSolde() {
    return solde;
  }

  public void setSolde(Double solde) {
    this.solde = solde;
  }

  public List<Operation> getOperations() {
    return operations;
  }

  public void setOperations(List<Operation> operations) {
    this.operations = operations;
  }

  public Banque getBanque() {
    return banque;
  }

  public void setBanque(Banque banque) {
    this.banque = banque;
  }

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

}
