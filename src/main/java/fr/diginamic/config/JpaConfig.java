package fr.diginamic.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaConfig {
  EntityManagerFactory factory = Persistence.createEntityManagerFactory("exo_3");
  EntityManager manager = factory.createEntityManager();
  EntityTransaction transaction = manager.getTransaction();

  public JpaConfig() {
  }

  public void close() {
    manager.close();
    factory.close();
  }

  public EntityManagerFactory getFactory() {
    return factory;
  }

  public void setFactory(EntityManagerFactory factory) {
    this.factory = factory;
  }

  public EntityManager getManager() {
    return manager;
  }

  public void setManager(EntityManager em) {
    this.manager = em;
  }

  public EntityTransaction getTransaction() {
    return transaction;
  }

  public void setTransaction(EntityTransaction tx) {
    this.transaction = tx;
  }

}
