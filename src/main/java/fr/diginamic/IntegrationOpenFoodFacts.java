package fr.diginamic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.context.JpaContext;
import fr.diginamic.dao.AllergeneDao;
import fr.diginamic.dao.CategorieDao;
import fr.diginamic.dao.IngredientDao;
import fr.diginamic.dao.MarqueDao;
import fr.diginamic.dao.ProduitDao;
import fr.diginamic.entities.Allergene;
import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Ingredient;
import fr.diginamic.entities.Marque;
import fr.diginamic.entities.Produit;

public class IntegrationOpenFoodFacts {
  /** Instance de JPA Context */
  static JpaContext config = new JpaContext();
  /** Instance de Logger */
  static Logger logger = LoggerFactory.getLogger(IntegrationOpenFoodFacts.class);

  public static void main(String[] args) throws IOException, InterruptedException {

    // #region DAO Values
    CategorieDao categorieDao = new CategorieDao(config.getManager(), logger);
    MarqueDao marqueDao = new MarqueDao(config.getManager(), logger);
    IngredientDao ingredientDao = new IngredientDao(config.getManager(), logger);
    AllergeneDao allergeneDao = new AllergeneDao(config.getManager(), logger);
    ProduitDao produitDao = new ProduitDao(config.getManager(), logger);
    // #endregion

    // #region Variable
    List<String> ingredientsToPush = new ArrayList<>();
    List<String> allergenesToPush = new ArrayList<>();
    List<HashMap<String, String>> foodList = new ArrayList<>();
    String path = IntegrationOpenFoodFacts.class.getClassLoader().getResource("open-food-facts.csv").getPath();
    FileReader file = new FileReader(path);
    Scanner sc = new Scanner(file);
    // #endregion

    // #region Reading CSV File
    sc.nextLine(); // exclude header line of csv

    while (sc.hasNextLine()) {
      String[] lines = sc.nextLine().split("\\|");
      HashMap<String, String> map = new HashMap<>();

      if (lines.length < 30) {
        map.put("categorie", lines[0].replaceAll("\"", "")
            .toLowerCase());
        map.put("marque", lines[1].replaceAll("\"", "")
            .toLowerCase());
        map.put("nom", lines[2].replaceAll("\"", "")
            .toLowerCase());
        map.put("ingredients", lines[4].replaceAll("\"", "")
            .toLowerCase());
        map.put("allergenes", "");
      } else {
        map.put("categorie", lines[0].replaceAll("\"", "")
            .toLowerCase());
        map.put("marque", lines[1].replaceAll("\"", "")
            .toLowerCase());
        map.put("nom", lines[2].replaceAll("\"", "")
            .toLowerCase());
        map.put("ingredients", lines[4].replaceAll("\"", "")
            .toLowerCase());
        map.put("allergenes", lines[28].replaceAll("\"", "")
            .toLowerCase());
      }
      foodList.add(map);
    }

    sc.close(); // close the scanner
    // #endregion

    // #region Add Categories, Marques, Ingredients, Allergenes

    config.getTransaction().begin(); // Begin Transaction

    // Clear terminal
    logger.info("\033[H\033[2J");
    logger.info("Waiting for categories list insertions...");

    // Add Categories //
    for (HashMap<String, String> food : foodList) {
      Categorie cat = new Categorie();
      cat.setLibelle(food.get("categorie"));
      categorieDao.add(cat);
    }

    // Clear terminal
    logger.info("\033[H\033[2J");
    logger.info("Waiting for marques list insertions...");

    // Add Marques //
    for (HashMap<String, String> food : foodList) {
      Marque marque = new Marque();
      marque.setLibelle(food.get("marque"));
      marqueDao.add(marque);
    }

    // Clear terminal
    logger.info("\033[H\033[2J");
    logger.info("Waiting for ingredients list insertions...");

    // Add Ingredients //
    for (int i = 0; i < foodList.size(); i++) {
      List<String> ingredientList = Arrays.asList(foodList.get(i).get("ingredients").split("[,;-]+"));
      for (int j = 0; j < ingredientList.size(); j++) {
        String data = ingredientList.get(j).trim();
        if (ingredientsToPush.stream().filter(occ -> occ.equals(data)).count() == 0
            && data.length() < 255) {
          ingredientsToPush.add(data);
        }
      }
    }

    // Clear terminal
    logger.info("\033[H\033[2J");

    for (int i = 0; i < ingredientsToPush.size(); i++) {
      Ingredient ingredient = new Ingredient();
      ingredient.setLibelle(ingredientsToPush.get(i));
      ingredientDao.add(ingredient);
    }

    // Clear terminal
    logger.info("\033[H\033[2J");
    logger.info("Waiting for allergene list insertions...");

    // Add Allergenes //
    for (int i = 0; i < foodList.size(); i++) {

      List<String> allergenesList = Arrays.asList(foodList.get(i).get("allergenes").split("[,;-]+"));

      for (int j = 0; j < allergenesList.size(); j++) {
        String data = allergenesList.get(j).trim();
        if (allergenesToPush.stream().filter(occ -> occ.equals(data)).count() == 0 &&
            data.length() < 255) {
          allergenesToPush.add(data);
        }
      }
    }

    // Clear terminal
    logger.info("\033[H\033[2J");

    for (int i = 0; i < allergenesToPush.size(); i++) {
      Allergene allergene = new Allergene();
      allergene.setLibelle(allergenesToPush.get(i));
      allergeneDao.add(allergene);
    }

    config.getTransaction().commit();
    // #endregion

    // #region Add Produits

    config.getTransaction().begin(); // Start Transaction

    List<Ingredient> ingredientsProduit = ingredientDao.getAll();
    List<Allergene> allergeneProduit = allergeneDao.getAll();
    List<Marque> marqueProduit = marqueDao.getAll();
    List<Categorie> categorieProduit = categorieDao.getAll();
    List<Produit> produitToInsert = new ArrayList<>();

    // Clear terminal
    logger.info("\033[H\033[2J");
    logger.info("Waiting for product list insertions...");

    for (int i = 0; i < foodList.size(); i++) {
      Produit new_produit = new Produit();
      List<String> data_ingredients = Arrays.asList(foodList.get(i).get("ingredients").split("[,;-]+"));
      List<String> data_allergenes = Arrays.asList(foodList.get(i).get("allergenes").split("[,;-]+"));

      new_produit.setNom(foodList.get(i).get("nom"));
      new_produit.setIngredients(new ArrayList<>());
      new_produit.setAllergenes(new ArrayList<>());

      // Attribute marque
      for (Marque marque : marqueProduit) {
        if (marque.getLibelle().equals(foodList.get(i).get("marque"))) {
          new_produit.setMarque(marque);
          break;
        }
      }

      // Attribute categorie
      for (Categorie cate : categorieProduit) {
        if (cate.getLibelle().equals(foodList.get(i).get("categorie"))) {
          new_produit.setCategorie(cate);
          break;
        }
      }

      // Attribute ingredients
      for (String string : data_ingredients) {
        Optional<Ingredient> obj = ingredientsProduit.stream().filter(occ -> occ.getLibelle().equals(string))
            .findFirst();
        if (obj.isPresent()) {
          new_produit.getIngredients().add(obj.get());
        }
      }

      // Attribute allergenes
      for (String string : data_allergenes) {
        Optional<Allergene> obj = allergeneProduit.stream().filter(occ -> occ.getLibelle().equals(string))
            .findFirst();
        if (obj.isPresent()) {
          new_produit.getAllergenes().add(obj.get());
        }
      }

      produitToInsert.add(new_produit);
    }

    for (Produit produit : produitToInsert) {
      produitDao.add(produit);
    }

    logger.info("\033[H\033[2J");
    logger.info("All products inserted");

    // Commit Transaction
    config.getTransaction().commit();
    // #endregion

    // End EntityManager
    config.close();
  }
}
