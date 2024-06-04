package fr.diginamic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.config.JpaConfig;
import fr.diginamic.entities.Adresse;
import fr.diginamic.entities.Banque;
import fr.diginamic.entities.Client;
import fr.diginamic.entities.Compte;
import fr.diginamic.entities.Operation;

public class DemoBanque {
  public static void main(String[] args) {
    JpaConfig jpaConfig = new JpaConfig();
    Logger logger = LoggerFactory.getLogger(DemoBanque.class);

    // Begin Transaction
    jpaConfig.getTransaction().begin();

    // Create a bank
    Banque banque = new Banque();
    banque.setNom("BNP");
    jpaConfig.getManager().persist(banque);

    // create accounts
    List<Compte> comptes = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Compte compte = new Compte("FR_00000" + i, 0.0);
      compte.setBanque(banque);
      jpaConfig.getManager().persist(compte);
      comptes.add(compte);
    }
    comptes.forEach(c -> c.setOperations(new ArrayList<>()));

    // create operations
    List<Operation> operations = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Operation operation = new Operation();
      operation.setMontant(Math.floor(Math.random() * 100.0) + 0.0);
      operation.setMotif("Virement");
      operation.setCompte(comptes.get(i));
      jpaConfig.getManager().persist(operation);
      operations.add(operation);
      comptes.get(i).getOperations().add(operation);
    }

    // update comptes
    for (int i = 0; i < comptes.size(); i++) {
      comptes.get(i).setSolde(comptes.get(i).getOperations().get(0).getMontant());
    }
    comptes.forEach(c -> jpaConfig.getManager().merge(c));

    // create clients
    Client client1 = new Client();
    Client client2 = new Client();

    Adresse adresse1 = new Adresse();
    Adresse adresse2 = new Adresse();
    // set address 1
    adresse1.setNumero(1);
    adresse1.setRue("rue de la paix");
    adresse1.setCodePostal(75000);
    adresse1.setVille("Paris");

    // set address 2
    adresse2.setNumero(2);
    adresse2.setRue("rue de la paix");
    adresse2.setCodePostal(75000);
    adresse2.setVille("Paris");

    // set client 1
    client1.setNom("Dupont");
    client1.setPrenom("Jean");
    client1.setDateNaissance(LocalDate.now());
    client1.setAdresse(adresse1);

    // set client 2
    client2.setNom("Durand");
    client2.setPrenom("Marie");
    client2.setDateNaissance(LocalDate.now());
    client2.setAdresse(adresse2);

    // persist client 1
    jpaConfig.getManager().persist(client1);

    // persist client 2
    jpaConfig.getManager().persist(client2);

    // associate client 1 to account 1
    client1.setComptes(new ArrayList<>());
    client1.getComptes().add(comptes.get(0));
    client1.getComptes().add(comptes.get(1));
    client1.getComptes().add(comptes.get(2));
    client1.getComptes().add(comptes.get(3));
    client1.getComptes().add(comptes.get(9));

    // associate client 2 to account 2
    client2.setComptes(new ArrayList<>());
    client2.getComptes().add(comptes.get(4));
    client2.getComptes().add(comptes.get(5));
    client2.getComptes().add(comptes.get(6));
    client2.getComptes().add(comptes.get(7));
    client2.getComptes().add(comptes.get(8));
    client2.getComptes().add(comptes.get(9));
    jpaConfig.getManager().merge(client2);

    // logger
    logger.info("Banque: " + banque.getNom());
    logger.info("----------------------------");
    logger.info("Liste de comptes: \n");
    comptes.forEach(c -> logger.info(c.getId() + " - " + c.getSolde()));
    logger.info("----------------------------");
    logger.info("Liste d'operations: \n");
    operations.forEach(o -> logger.info(o.getId() + " - " + o.getMotif()));
    logger.info("----------------------------");
    logger.info("Client 1: \n");
    logger.info(client1.getId() + " - " + client1.getNom() + " - " + client1.getPrenom() + " - "
        + client1.getAdresse().getVille());
    logger.info("Comptes associes: \n");
    client1.getComptes().forEach(c -> logger.info(c.getId() + " - " + c.getSolde()));
    logger.info("----------------------------");
    logger.info(client2.getId() + " - " + client2.getNom() + " - " + client2.getPrenom() + " - "
        + client2.getAdresse().getVille());
    logger.info("Comptes associes: \n");
    client2.getComptes().forEach(c -> logger.info(c.getId() + " - " + c.getSolde()));
    logger.info("----------------------------");

    // Commit Transaction
    jpaConfig.getTransaction().commit();

    // Close JPA context
    jpaConfig.close();
  }
}
