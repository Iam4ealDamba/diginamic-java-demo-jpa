package fr.diginamic.dao;

import java.util.List;

import org.slf4j.Logger;

import fr.diginamic.dao.interfaces.CategorieDaoInterface;
import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CategorieDao implements CategorieDaoInterface {
  /** Instance de EntityManager */
  EntityManager manager;
  /** Instance de Logger */
  Logger logger;

  /**
   * Constructeur
   * @param em
   * @param logger
   */
  public CategorieDao(EntityManager em, Logger logger) {
    this.manager = em;
    this.logger = logger;
  }

  @Override
  public void add(Categorie cat) {
    TypedQuery<Categorie> query = this.manager.createQuery(
        "SELECT c FROM Categorie c WHERE c.libelle = \"" + cat.getLibelle() + "\"",
        Categorie.class);
    List<Categorie> result = query.getResultList();

    if (result.size() == 0) {
      this.manager.persist(cat);
    }
  }

  @Override
  public void update(Categorie new_cat) {
    try {
      TypedQuery<Categorie> query = this.manager.createQuery(
          "UPDATE Categorie c SET c.libelle = " + new_cat.getLibelle() + " WHERE c.id = " + new_cat.getId(),
          Categorie.class);
      int result = query.executeUpdate();

      if (result < 0) {
        logger.error("La catégorie n'a pas pu être mise à jour", result);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue à l'update: ", e.getMessage());
    }

  }

  @Override
  public void delete(Categorie cat) {
    try {
      TypedQuery<Categorie> query = this.manager.createQuery(
          "SELECT c FROM Categorie c WHERE c.id = " + cat.getId(),
          Categorie.class);
      int result = query.getResultList().size();

      if (result < 0) {
        this.manager.remove(cat);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au delete: ", e.getMessage());
    }
  }

  @Override
  public List<Categorie> getAll() {
    TypedQuery<Categorie> query = this.manager.createQuery(
        "SELECT c FROM Categorie c",
        Categorie.class);
    List<Categorie> result = query.getResultList();

    if (result.size() > 0) {
      return result;
    }
    return null;
  }

  @Override
  public Categorie getById(Long id) {
    try {
      TypedQuery<Categorie> query = this.manager.createQuery(
          "SELECT c FROM Categorie c WHERE c.id = " + id,
          Categorie.class);
      Categorie result = query.getSingleResult();

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
  public Categorie getByLibelle(String libelle) {
    try {
      TypedQuery<Categorie> query = manager.createQuery("select m from categorie m where m.libelle = :libelle",
          Categorie.class);
      query.setParameter("libelle", libelle);
      return query.getSingleResult();
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au getByLibelle: ", e.getMessage());
      return null;
    }
  }
}
