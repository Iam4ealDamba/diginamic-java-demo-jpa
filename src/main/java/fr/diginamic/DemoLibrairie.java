package fr.diginamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.config.JpaConfig;
import fr.diginamic.entities.Client;
import fr.diginamic.entities.Emprunt;
import fr.diginamic.entities.Livre;

public class DemoLibrairie {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(DemoLibrairie.class);
    logger.info("Hello World");

    JpaConfig config = new JpaConfig();

    // Debut transaction
    config.getTransaction().begin();

    // Recupere la liste des livres
    Livre livres = config.getManager().find(Livre.class, 1);
    Client client = config.getManager().find(Client.class, 1);

    logger.info("Livre : " + livres.getTitle() + " - " + livres.getId());
    logger.info("Client: " + client.getNom() + " " + client.getPrenom() + " - " + livres.getId());
    for (Emprunt emprunt : client.getEmprunts()) {
      logger.info("Emprunt: " + emprunt.toString());
    }

    // Fin transaction
    config.getTransaction().commit();

    // Fermeture
    config.close();
  }
}
