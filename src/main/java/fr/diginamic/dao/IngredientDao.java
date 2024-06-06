package fr.diginamic.dao;

import java.util.List;

import org.slf4j.Logger;

import fr.diginamic.dao.interfaces.IngredientDaoInterface;
import fr.diginamic.entities.Ingredient;
import fr.diginamic.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class IngredientDao implements IngredientDaoInterface {
  /** Instance de EntityManager */
  EntityManager manager;
  /** Instance de Logger */
  Logger logger;

  /**
   * Constructeur
   * 
   * @param em
   * @param logger
   */
  public IngredientDao(EntityManager em, Logger logger) {
    this.manager = em;
    this.logger = logger;
  }

  @Override
  public Ingredient add(Ingredient ingredient) {
    this.manager.persist(ingredient);
    return ingredient;
  }

  @Override
  public void update(Ingredient new_marque) {
    try {
      TypedQuery<Ingredient> query = this.manager.createQuery(
          "UPDATE Ingredient i SET i.libelle = " + new_marque.getLibelle() + " WHERE i.id = " + new_marque.getId(),
          Ingredient.class);
      int result = query.executeUpdate();

      if (result < 0) {
        logger.error("La ingredient n'a pas pu être mise à jour", result);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue à l'update: ", e.getMessage());
    }

  }

  @Override
  public void delete(Ingredient ingredient) {
    try {
      TypedQuery<Ingredient> query = this.manager.createQuery(
          "SELECT i FROM Ingredient i WHERE i.id = " + ingredient.getId(),
          Ingredient.class);
      int result = query.getResultList().size();

      if (result < 0) {
        this.manager.remove(ingredient);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au delete: ", e.getMessage());
    }
  }

  @Override
  public List<Ingredient> getAll() {
    TypedQuery<Ingredient> query = this.manager.createQuery(
        "SELECT i FROM Ingredient i",
        Ingredient.class);
    List<Ingredient> result = query.getResultList();

    if (result.size() > 0) {
      return result;
    }
    return null;
  }

  @Override
  public Ingredient getById(Long id) {
    try {
      TypedQuery<Ingredient> query = this.manager.createQuery(
          "SELECT i FROM Ingredient i WHERE i.id = " + id,
          Ingredient.class);
      Ingredient result = query.getSingleResult();

      if (result != null) {
        return result;
      }
      return null;

    } catch (Exception e) {
      logger.error("Une erreur est intervenue au getById: ", e.getMessage());
      return null;
    }
  }

  @Override
  public Ingredient getByLibelle(String libelle) {
    try {
      logger.info("libelle: " + libelle);
      TypedQuery<Ingredient> query = manager.createQuery(
          "select i from Ingredient i where i.libelle = \"" + libelle.trim() + "\"",
          Ingredient.class);
      Ingredient result = null;

      if (query.getSingleResult() != null) {
        result = query.getSingleResult();
        logger.info(result.toString());
        return query.getSingleResult();
      } else {
        return null;
      }

    } catch (Exception e) {
      logger.error("Ingredient - Une erreur est intervenue au getByLibelle: ", e);
      return null;
    }
  }
}
