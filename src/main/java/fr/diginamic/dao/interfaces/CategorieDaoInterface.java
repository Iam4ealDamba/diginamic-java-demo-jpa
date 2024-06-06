package fr.diginamic.dao.interfaces;

import java.util.List;

import fr.diginamic.entities.Allergene;
import fr.diginamic.entities.Categorie;

public interface CategorieDaoInterface {

  /**
   * Retourne la liste des catégories
   * 
   * @return
   */
  public List<Categorie> getAll();

  /**
   * Retourne une catégorie par son id
   * 
   * @param id
   */
  public Categorie getById(Long id);

  /**
   * Retourne une catégorie par son libelle
   * 
   * @param libelle
   * @return
   */
  public Categorie getByLibelle(String libelle);

  /**
   * Ajoute une nouvelle catégorie
   * 
   * @param cat
   */
  public void add(Categorie cat);

  /**
   * Met a jour une catégorie
   * 
   * @param new_cat
   */
  public void update(Categorie new_cat);

  /**
   * Supprime une catégorie
   * 
   * @param cat
   */
  public void delete(Categorie cat);

}
