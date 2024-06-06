### Projet Dem -JPA - Hibernate
**IMPORTANT - Pour retrouver les autre exercices, veuillez regarder sur Github les branches de ce projet.**

---

### JPA– TP 1

OBJECTIF - Prendre en main JPA avec un accès simple en base de données

**<u>CREATION DE LA BASE DE DONNEES</u>**

- Via PhpMyAdmin, créer une base de données appelée recensement
- Créer une table appelée REGION
  - Avec une colonne ID de type int
  - Avec une colonne NOM de type VARCHAR(20) not null
- Insérer 2 régions au choix
  - CREATION DU PROJET
- Créer un projet MAVEN appelé demo-jpa
- Créer un dépôt Git en local pour ce projet
- Créer le fichier .gitignore avec exclusion de /target, de /.settings, .project et .classpath
- Créer le dépôt GitHub avec le même nom
- Ajoutez les dépendances suivantes dans votre pom.xml

  - La librairie mariadb-java-client (ou mysql-connector-java suivant votre BDD)
  - La librairie suivante :

    ```xml
    <!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
    <dependency>
      <groupId>org.hibernate.orm</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>6.5.2.Final</version>
    </dependency>-
    ```

  - Dans src/main/resources :
  - Créer un répertoire META-INF
  - Créer le fichier persistence.xml dans META-INF

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
    https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
    version="3.0">
      <persistence-unit name="pu_essai"    transaction-type="RESOURCE_LOCAL"> <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider> <properties>
        <property name="jakarta.persistence.jdbc.url" value=""/>
        <property name="jakarta.persistence.jdbc.user" value=""/>
        <property name="jakarta.persistence.jdbc.password" value=""/>
        <property name="hibernate.show_sql" value="true"/>
        </properties>
      </persistence-unit>
    </persistence>
    ```

- Valoriser les différentes variables (javax.persistence.jdbc.url, user et password) dans le fichier persistence.xml pour accéder à votre votre base de données de recensement de population.
- Créez un package fr.diginamic
- Créer une classe ConnexionJpa de type application exécutable
- Dans la méthode principale :
  -Créer une instance d’entityManagerFactory
  -Créer une instance d’entityManager
  -Vérifier que vous vous connectez bien à la base de données (le fait que des logs s’affichent en rouge suffisent à prouver la connexion).
  -Créer une classe Region mappée sur votre table des régions.
  -Extraire une région de votre choix à partir de son identifiant.
  -Insérer une nouvelle région en base de données
  -Vérifier que la nouvelle région est bien présente en base de données.
