#### TP n°4 – Modélisation objet avec JPA

##### Objectifs - Se renforcer dans la modélisation JPA et notamment l’utilisation des annotations relationnelles.

Description

- Voici le modèle UML d’une application bancaire. Nous allons créez les entités JPA et essayez de générer les tables à partir des entités.
- Créez un nouveau projet MAVEN banque-jpa
- Créez une nouvelle base de données banque
- Dans le répertoire src/main/resources, créez le répertoire META-INF
- Dans le répertoire META-INF, mettez en place le fichier persistence.xml
- Valorisez la persistence-unit dans persistence.xml pour y accéder
- Positionner la propriété suivante dans persistence.xml pour regénérer automatiquement le schema à chaque redémarrage de l’application:

  ```xml
  <property name="jakarta.persistence.schema-generation.database.action"    value="drop-and- create"/>
  ```

- Créer les entités JPA suivantes et mettez en place les annotations relationnelles :
  o Banque
  o Compte
  o Operation
  o Client
  ▪ Adresse (Embeddable)
- Les tables seront générées à partir des relations définies dans votre modèle métier
- Insérer en base de données des instances des différents objets en utilisant l’EntityManager Quelques propriétés utiles pour le persistence.xml :
- Cette propriété ne créé que les nouvelles tables après chaque redémarrage:

  ```xml
  <property name="jakarta.persistence.schema-generation.database.action" value="update"/>
  ```
