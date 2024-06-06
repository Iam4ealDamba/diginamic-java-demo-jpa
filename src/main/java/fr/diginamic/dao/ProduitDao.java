package fr.diginamic.dao;

import java.util.List;

import org.slf4j.Logger;

import fr.diginamic.dao.interfaces.ProduitDaoInterface;
import fr.diginamic.entities.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProduitDao implements ProduitDaoInterface {
  /** Instance de EntityManager */
  EntityManager manager = null;
  /** Instance de Logger */
  Logger logger = null;

  /**
   * Constructeur
   * @param em
   * @param logger
   */
  public ProduitDao(EntityManager em, Logger logger) {
    this.manager = em;
    this.logger = logger;
  }

  @Override
  public List<Produit> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public Produit getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  @Override
  public void add(Produit produit) {
    this.manager.persist(produit);
  }

  @Override
  public void update(Produit new_produit) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Produit produit) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Produit getByLibelle(String libelle) {
    try {
      TypedQuery<Produit> query = manager.createQuery("select p from produit p where p.libelle = :libelle",
          Produit.class);
      query.setParameter("libelle", libelle);
      return query.getSingleResult();
    } catch (Exception e) {
      logger.error("Une erreur est intervenue au getByLibelle: ", e.getMessage());
      return null;
    }
  }

}
