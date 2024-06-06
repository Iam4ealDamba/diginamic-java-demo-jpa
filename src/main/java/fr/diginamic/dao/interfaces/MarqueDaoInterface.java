package fr.diginamic.dao.interfaces;

import java.util.List;

import fr.diginamic.entities.Marque;

public interface MarqueDaoInterface {

  /**
   * Retourne une liste de marques.
   */
  public List<Marque> getAll();

  /**
   * Retourne une marque par son id.
   * 
   * @param id
   * @return
   */
  public Marque getById(Long id);

  /**
   * Retourne une marque par son libelle.
   * 
   * @param libelle
   * @return
   */
  public Marque getByLibelle(String libelle);

  /**
   * Ajoute une nouvelle marque
   * 
   * @param marque
   */
  public void add(Marque marque);

  /**
   * Met a jour une marque
   * 
   * @param new_marque
   */
  public void update(Marque new_marque);

  /**
   * Supprime une marque
   * 
   * @param marque
   */
  public void delete(Marque marque);

}
