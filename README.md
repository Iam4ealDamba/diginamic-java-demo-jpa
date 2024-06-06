### TP n°7 – Implémentation Jpa sur le projet open food facts

---

##### Objectifs - Vous connaissez peut-être l’application Yuka, disponible sur smartphone. Yuka fournit des informations nutritionnelles sur pratiquement tous les produits alimentaires commercialisés en France. En plus d’informations, elle fournit également un score nutritionnel de A (excellent) à F (mauvais).

##### Cette application à succès s’est construite sur une base de données open source appelée Open Food Facts.

##### La base de données Open Food Facts est une base de données mondiale, qu’on peut télécharger sous la forme d’un fichier CSV. Le fichier que je vais vous demander de traiter dans le cadre de ce TP est le même que celui sur lequel s’est basé Yuka. Il ne concerne que les les produits alimentaires fabriqués en France.

##### Dans ce TP vous allez mettre en base de données un fichier open food facts contenant près de 13432 produits commercialisés en France.

**<u>Description</u>**
Développez une nouvelle application permettant d’intégrer en base de données le fichier open food facts.
Le fichier fourni comporte 13 432 références avec 30 informations associées par produit :

- catégorie
- marque
- nom
- score nutrionnel : A (excellent) à F (Faible)
- liste des ingrédients
- énergie pour 100g (en joules)
- quantité de graisse pour 100g
- liste des allergènes
- liste des additifs
- présence d’huile de palme
- toutes les vitamines,
- calcium, magnesium, etc..
  Au total chaque référence de produit a 30 attributs.

**<u>Type de fichier</u>**
Le fichier fourni est un fichier CSV.Un fichier CSV est un fichier dans lesquelles les informations sont séparées par des séparateurs. Ici le séparateur utilisé est le | :
- la première ligne fait la liste des attributs séparés par des caractères |
- chaque ligne suivante correspond à un article et les valeurs des attributs sont séparées par des |
  - la liste des ingrédients est séparée par des virgules, parfois des ; ou encore des tirets.
  - la liste des additifs et des allergènes également
  - Attention certaines données, notamment chiffrées, ne sont pas toujours renseignées.
Instructions
- Créez un nouveau projet maven nommé traitement-fichier-jpa-off
- Poussez-le sur GitHub
- Créez une nouvelle base de données appelée open-food-facts.
- Créez une classe exécutable IntegrationOpenFoodFacts.
- Vous devrez mettre en place des DAO afin de gérer vos diverses entités. Pas de requête en dehors des DAO !!
- Précautions à prendre :
  - Mettez en place les classes suivantes : Marque, Categorie, Produit, Ingredient, Allergene.
    - Attention pas de doublons dans les tables catégorie, marque, ingrédient et allergène. Par exemple le sucre ne doit apparaitre qu’une seule fois dans la table des ingrédients. Le sucre peut donc être associé à plusieurs produits.
    - Attention aux cardinalités entre classes et l’impact sur le modèle de données.
  - Il n’est pas question d’avoir des doublons dans les tables.
  - Votre programme doit être rejouable, c’est-à-dire qu’il est capable de vérifier si une donnée existe déjà ou non avant de l’intégrer.
  - Si un produit ne satisfait pas aux contrôles, par exemple si vous n’arrivez pas à parser la liste des ingrédients, vous devez le stocker dans une table des erreurs.
  - Plus difficile : Votre programme est capable de reprendre là où il en était en cas de plantage.
  - Exigences qualités : Vous devez mettre en place la javadoc.
Livrables attendus
- L’application
- Le fichier SQL contenant la structure des tables.

**COMMITEZ**
