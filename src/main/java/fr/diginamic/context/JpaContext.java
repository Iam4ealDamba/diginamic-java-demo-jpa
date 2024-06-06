package fr.diginamic.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaContext {
  /** Factory pour l'EntityManager */
  EntityManagerFactory factory = Persistence.createEntityManagerFactory("exo_7");

  /** L'EntityManager pour les requêtes */
  EntityManager manager = factory.createEntityManager();

  /** La Transaction pour les requêtes */
  EntityTransaction transaction = manager.getTransaction();

  /** Constructeur par défaut */
  public JpaContext() {
  }

  /** Ferme l'EntityManager et la Factory */
  public void close() {
    manager.close();
    factory.close();
  }

  /** Retourne la Factory */
  public EntityManagerFactory getFactory() {
    return factory;
  }

  /** Retourne l'EntityManager */
  public EntityManager getManager() {
    return manager;
  }

  /** Retourne la Transaction */
  public EntityTransaction getTransaction() {
    return transaction;
  }

}
