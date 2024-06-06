package fr.diginamic.dao;

import java.util.List;

import org.slf4j.Logger;

import fr.diginamic.dao.interfaces.MarqueDaoInterface;
import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Marque;
import fr.diginamic.entities.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class MarqueDao implements MarqueDaoInterface {
  /** Instance de EntityManager */
  EntityManager manager;
  /** Instance de Logger */
  Logger logger;

  /**
   * Constructeur
   * @param em
   * @param logger
   */
  public MarqueDao(EntityManager em, Logger logger) {
    this.manager = em;
    this.logger = logger;
  }

  @Override
  public void add(Marque marque) {
    TypedQuery<Marque> query = this.manager.createQuery(
        "SELECT m FROM Marque m WHERE m.libelle = \"" + marque.getLibelle() + "\"",
        Marque.class);
    List<Marque> result = query.getResultList();

    if (result.size() == 0) {
      this.manager.persist(marque);
    }
  }

  @Override
  public void update(Marque new_marque) {
    try {
      TypedQuery<Marque> query = this.manager.createQuery(
          "UPDATE Marque m SET m.libelle = " + new_marque.getLibelle() + " WHERE m.id = " + new_marque.getId(),
          Marque.class);
      int result = query.executeUpdate();

      if (result < 0) {
        logger.error("La marque n'a pas pu être mise à jour", result);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue à l'update: ", e.getMessage());
    }

  }

  @Override
  public void delete(Marque marque) {
    try {
      TypedQuery<Marque> query = this.manager.createQuery(
          "SELECT m FROM Marque m WHERE m.id = " + marque.getId(),
          Marque.class);
      int result = query.getResultList().size();

      if (result < 0) {
        this.manager.remove(marque);
      }
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au delete: ", e.getMessage());
    }
  }

  @Override
  public List<Marque> getAll() {
    TypedQuery<Marque> query = this.manager.createQuery(
        "SELECT m FROM Marque m",
        Marque.class);
    List<Marque> result = query.getResultList();

    if (result.size() > 0) {
      return result;
    }
    return null;
  }

  @Override
  public Marque getById(Long id) {
    try {
      TypedQuery<Marque> query = this.manager.createQuery(
          "SELECT m FROM Marque m WHERE m.id = " + id,
          Marque.class);
      Marque result = query.getSingleResult();

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
  public Marque getByLibelle(String libelle) {
    try {
      TypedQuery<Marque> query = manager.createQuery("select m from marque m where m.libelle = :libelle",
          Marque.class);
      query.setParameter("libelle", libelle);
      return query.getSingleResult();
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au getByLibelle: ", e.getMessage());
      return null;
    }
  }
}
