package fr.diginamic.dao.interfaces;

import java.util.List;

import fr.diginamic.entities.Ingredient;

public interface IngredientDaoInterface {

  /**
   * Retourne une liste d'ingredients.
   * 
   * @return
   */
  public List<Ingredient> getAll();

  /**
   * Retourne un ingredient par son id.
   * 
   * @param id
   * @return
   */
  public Ingredient getById(Long id);

  /**
   * Retourne un ingredient par son libelle.
   * 
   * @param libelle
   * @return
   */
  public Ingredient getByLibelle(String libelle);

  /**
   * Ajoute un nouvel ingredient
   * 
   * @param ingredient
   * @return
   */
  public Ingredient add(Ingredient ingredient);

  /**
   * Met a jour un ingredient
   * 
   * @param new_ingredient
   */
  public void update(Ingredient new_ingredient);

  /**
   * Supprime un ingredient
   * 
   * @param ingredient
   */
  public void delete(Ingredient ingredient);

}
