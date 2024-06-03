package fr.diginamic;

import java.util.List;

import fr.diginamic.entities.Region;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConnexionJpa {
  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_essai");
    EntityManager em = factory.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    // Begin transaction
    transaction.begin();

    // CRUD
    // Récupération d'une région par son id
    Region region = em.find(Region.class, 1);
    if (region != null) {
      System.out.println(region);
    } else {
      System.out.println("Région non trouvée");
    }

    // Inserer une nouvelle région
    Region newRegion = new Region();
    newRegion.setName("Nouvelle région");
    em.persist(newRegion);
    System.out.println("New Region: " + newRegion);

    // End transaction
    transaction.commit();

  }
}
