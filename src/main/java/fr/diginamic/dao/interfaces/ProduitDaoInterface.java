package fr.diginamic.dao.interfaces;

import java.util.List;

import fr.diginamic.entities.Produit;

public interface ProduitDaoInterface {

  /**
   * Retourne une liste de marques.
   * @return
   */
  public List<Produit> getAll();

  /**
   * Retourne une marque par son id.
   * @param id
   * @return
   */
  public Produit getById(Long id);

  /**
   * Retourne une marque par son libelle.
   * @param libelle
   * @return
   */
  public Produit getByLibelle(String libelle);

  /**
   * Ajoute une nouvelle marque
   * @param produit
   */
  public void add(Produit produit);

  /**
   * Met a jour une marque
   * @param new_produit
   */
  public void update(Produit new_produit);

  /**
   * Supprime une marque
   * @param produit
   */
  public void delete(Produit produit);

}
