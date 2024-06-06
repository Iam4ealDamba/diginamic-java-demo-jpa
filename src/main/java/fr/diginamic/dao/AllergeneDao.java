package fr.diginamic.dao;

import java.util.List;

import org.slf4j.Logger;

import fr.diginamic.dao.interfaces.AllergeneDaoInterface;
import fr.diginamic.entities.Allergene;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AllergeneDao implements AllergeneDaoInterface {
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
  public AllergeneDao(EntityManager em, Logger logger) {
    this.manager = em;
    this.logger = logger;
  }

  @Override
  public List<Allergene> getAll() {
    TypedQuery<Allergene> query = this.manager.createQuery(
        "SELECT a FROM Allergene a",
        Allergene.class);
    List<Allergene> result = query.getResultList();

    if (result.size() > 0) {
      return result;
    }
    return null;
  }

  @Override
  public Allergene add(Allergene allergene) {
    this.manager.persist(allergene);
    return allergene;
  }

  @Override
  public Allergene getByLibelle(String libelle) {
    try {
      logger.info("libelle: " + libelle);
      TypedQuery<Allergene> query = manager.createQuery(
          "select m from Allergene m where m.libelle = \"" + libelle + "\"",
          Allergene.class);
      Allergene result = query.getSingleResult();
      logger.info(result.toString());
      return result;
    } catch (Exception e) {
      logger.error("Allergene - Une erreur est intervenue au getByLibelle: ", e);
      return null;
    }
  }

  @Override
  public Allergene getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public void update(Allergene new_allergene) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Allergene allergene) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

}
