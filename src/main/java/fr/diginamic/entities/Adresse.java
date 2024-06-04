package fr.diginamic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
  // ----------- Columns -------------

  @Column
  private Integer numero;

  @Column
  private String rue;

  @Column
  private Integer codePostal;

  @Column
  private String ville;

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getRue() {
    return rue;
  }

  public void setRue(String rue) {
    this.rue = rue;
  }

  public Integer getCodePostal() {
    return codePostal;
  }

  public void setCodePostal(Integer codePostal) {
    this.codePostal = codePostal;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  @Override
  public String toString() {
    return "Adresse [numero=" + numero + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
  }

}
