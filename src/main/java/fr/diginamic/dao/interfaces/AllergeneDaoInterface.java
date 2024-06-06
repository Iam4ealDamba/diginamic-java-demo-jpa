package fr.diginamic.dao.interfaces;

import java.util.List;

import fr.diginamic.entities.Allergene; 

public interface AllergeneDaoInterface {

  /**
   * Retourne la liste des allergènes
   * @return
   */
  public List<Allergene> getAll();

  /**
   * Retourne une allergène par son id
   * @param id
   * @return
   */
  public Allergene getById(Long id);

  /**
   * Retourne une allergène par son libelle
   * @param libelle
   * @return
   */
  public Allergene getByLibelle(String libelle);

  /**
   * Ajoute une nouvelle allergène
   * @param allergene
   * @return
   */
  public Allergene add(Allergene allergene);

  /**
   * Mettre à jour une allergène
   * @param new_allergene
   */
  public void update(Allergene new_allergene);

  /**
   * Supprime une allergène
   * @param allergene
   */
  public void delete(Allergene allergene);

}
