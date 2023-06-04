# Rapport de projet SAE 2 - 1256

## Pierre Fromont Boissel ,Roxane Zaharia, Glen Denoual, Augustin Lecomte

## Table des matières

- [Rapport de projet SAE 2 - 1256](#rapport-de-projet-sae-2---1256)
	- [Pierre Fromont Boissel ,Roxane Zaharia, Glen Denoual, Augustin Lecomte](#pierre-fromont-boissel-roxane-zaharia-glen-denoual-augustin-lecomte)
	- [Table des matières](#table-des-matières)
	- [Résumé du projet](#résumé-du-projet)
	- [Explication de notre MVC](#explication-de-notre-mvc)
	- [Réalisation de l'application](#réalisation-de-lapplication)
		- [Modèle et mise en place du projet](#modèle-et-mise-en-place-du-projet)
		- [Mise en place du git](#mise-en-place-du-git)
		- [Vue](#vue)
		- [Contrôleur](#contrôleur)
	- [Test](#test)
	- [Détails complémentaires](#détails-complémentaires)
		- [Conventions](#conventions)
		- [La sérilisation des données](#la-sérilisation-des-données)
		- [Participants](#participants)
		- [Rôles](#rôles)
		- [Pistes d'amélioration possibles](#pistes-damélioration-possibles)
		- [Package](#package)
		- [rapport](#rapport)

## Résumé du projet

Le projet **SAE 2 - 1256** consiste à réaliser une application type Trello, en utilisant le langage *Java* et la bibliothèque graphique *Java Swing*. Cette application doit permettre de gérer des projets, des tâches et des utilisateurs. Nous avons au préalable réalisé une étude de gestion de projet, et une étude de conception en UML. Nous avons ensuite réalisé l’application en elle-même, en suivant le patron **Modèle-Vue-Contrôleur** vu en cours.

## Explication de notre MVC

Pour ce projet, nous avons été fortement conseillé de choisir le modèle **MVC** pour la réalisation de notre application. Nous avons donc choisi de suivre ce modèle, et nous avons séparé notre application en trois packages distincts : le modèle, la vue et le contrôleur. Le **modèle** est la partie qui gère et traite les données. La **vue** est la partie qui permet l’affichage de l'application et des données sérilisés. Le **contrôleur** est la partie qui gère les interactions entre l'application et l'utilisateur en utilisant les évenements. Les boutons sont affichés dans la partie **vue**, mais sont reliés à dse *listeners* et des évenements qui sont eux dans la partie **contrôleur** come dans le **MVC** que la convention exige. Toutes ces parties sont indépendantes les unes des autres, mais sont mises en commun dans leurs méthodes et par la class *TrelloMain*. Nous avons aussi choisi de rajouter un **package style** qui contient les classes qui gèrent le style de l’application.

## Réalisation de l'application 

### Modèle et mise en place du projet
La première étape pour réaliser notre rojet a été de récupérer le code généré automatiquement du projet *Visual Paradigm* que nous avons dû réaliser pendant la partie **UML**. Ce fut assez simple, et, une fois le code généré, il a fallut coder les fonctions que nous avions prévu pendant cette phase analyse, autrement dit, nous avons effectué la partie **modèle**. Ceci n’a pas nécessairement été difficile, au vu de la complexité des fonctions demandés. En effet ces fonctions sont principalement des *getters* et des *setters*, ou des fonctions du type *add* ou *remove*. Il y a par exemple ces fonctions :

```java
public ArrayList<Card> getCards() { return cards; }

public void addCard(Card card) { cards.add(card); }
```

### Mise en place du git
Étant donné que l'étape cité plus haut ne requierait pas toute l'équipe pour être réalisée, nous avons décidé de mettre en place un *Git* pour simplifier le travail futur. L'autre partie du groupe a donc créé un dépôt *Git*, et **une branche par membre** du projet. Pour la suite du projet, à chaque fois que nous modifions le code, nous le faisions sur notre propre branche. Puis une fois le code implémenté, nous n'avions qu'à *push* sur notre propre branche et sur la branche main pour que tout le monde puisse avoir la bonne version du code. Quand nous avions besoin de recuperer le code de quelqu'un d'autre, nous n'avions qu'à merge notre branche avec le main, et nous étions à jour.
Cette étape à grandement facilité le travail, car nous n'avions pas à nous soucier de savoir si nous avions la bonne version du code, et nous pouvions travailler en parallèle sans problème.

### Vue
Une fois la partie modèle écrite et fonctionnelle, la deuxième étape a été de créer les interfaces graphiques, soit la partie vue de l'application. Nous avons alors créé les panel, en utilisant premièrement les JPanel issues directment de Java Swing. Puis après quelques fonctions réalisés, nous nous sommes rendu compte qu'il serait  pratique d'avoir une certaine unité entre les differentes vues réalisées. 
Par conséquent, l'idée de créer des classes qui implémentent directement les classes *Java Swing*, mais qui ont déja tout les attributs tel que la couleur ou les polices, nous est venu à l'esprit. Histoire de rendre le tout encore plus modulable, nous avons créer une interface qui a comme attributs les couleurs et les polices, et qui est implémenté par les classes qui gèrent les styles. Cela nous permet d'avoir un code plus propre, et plus facile à modifier. Par exemple, si nous voulions changer une couleur qui est présente sur les cartes, les listes et les tableaux, au lieu de devoir modifier chaque classe manuellement, nous n'avions qu'à changer une valeur dans l'interface. Toutes ces classes ont été placés dans le package *style*, afin de bien séparer les vues qui sont concrétement affichés sur l'écran contrairement à celle-ci qui servent des ces dites-vues.

### Contrôleur
Il nous fallait ensuite ajouter des controller a notre interface graphique, soit la partie controller de l'application. Nous avons alors créer les different controller necessaire pour le bon fonctionnement de l'application. Pour chaque controller nous avons du procéder à des testes comme : verifier si le nom du board qu'on veut créer n'est pas null, verifier si le workspace qu'on veux supprimer existe ... Cela nous a permis de n'avoir aucune incohérence et erreur. Les controller de création ou de suppression affiche une nouvelle fenetre qui vas indiquer a l'utilisateur ce qu'il doit faire et si il y a une erreur dans sa saisie.

## Test
Pendant notre codage, nous avons du à des moments tester nos fonctions et affichage. Pour ce faire, nous avons créer des main temporaire dans les classes que nous devions tester. 
Pour les vues cela était très utile pour placer plus facilement au bonne endroit chaque élement. Les main temporaire nous permettais aussi de voir si chacune des methode était fonctionelle et effectuait bien la tâche demander. 

## Détails complémentaires

### Conventions

Pour ce projet, nous avons décidé d'utiliser les conventions de nommage de Java. Cela nous permet d'avoir un code plus propre, et plus facile à lire. Par exemple, les noms de classes commencent par une majuscule, et les noms de variables commencent par une minuscule, comme dans la plus grande majorité des conventions Java. Nous avons aussi décidé de coder notre application **entiérement en anglais**. Cela nous permet d'avoir un code unifié en évitant le '*Franglais*' que nous aurions pu avoir si nous avions codé en français.

Voici un tableau récapitulatif des conventions de nommage que nous avons utilisé durant notre projet:
| Type 			| Convention 		| Exemple 	|
| --- 			| --- 				| ---		|
| Classes 		| UpperCamelCase 	| Card 		|
| Interfaces 	| UpperCamelCase 	| MyStyle	|
| Méthodes		| lowerCamelCase 	| getCards()|
| Variables 	| lowerCamelCase 	| firstName	|
| Constantes 	| UPPER_CASE 		| HEIGHT	|
| Packages 		| lowerCamelCase 	| views		|
| Enum 			| UpperCamelCase 	| Role		|
| Variables JSwing | lowerCamelCaseType	| titlePanel |

Aussi, nous avons decidé de mettre un point d'honneur sur les commentaires et la *Javadoc*. Ainsi, avant chaque méthode, même très simple, il y aura un bloc de commentaire qui explique ce que celle-ci fait, ce qu'elle prend en paramètre et ce qu'elle retourne. Cela nous permet d'avoir un code beacoup plus facile à comprendre. Ces commentaires sont structurés de la manière suivante :

```java
/**
 * This method does something
 * @param param1 ,type, this is the first parameter
 * @param param2 ,type, this is the second parameter
 * @return ,type, This is what the method returns
 * @author name of the author of the method
 * @see related type, method, class, etc...
 */
public type methodName(type param1, type param2) {
	// Code of the method
}
```

Enfin, pour rendre le code encore plus lisible, chaque "partie" d'une classe est découpé en blocs. Les blocs sont séparés et délimités par des **commentaires**. Par exemple, dans une classe *Card*, nous avons un bloc pour les attributs, un bloc pour les constructeurs, un bloc pour les *getters* et *setters*, etc... Cela nous permet d'avoir un code plus lisible, et plus facile à modifier. Voici un exemple de ce que cela donne :

```java
// -----------------------------------------------------
// ATTRIBUTES
// -----------------------------------------------------
private int id;

// -----------------------------------------------------
// CONSTRUCTORS
// -----------------------------------------------------
public Class() {
	// Code of the constructor
}

// -----------------------------------------------------
// GETTERS AND SETTERS
// -----------------------------------------------------
public int getId() {
	return id;
}
```

### La sérilisation des données
Pour que l'utilisateur puisse sauvegarder ses données entre les differentes utilisations de l'application, nous avons dû **sériliser** les objets. Cela permet de transformer les objets en un flux d'octets, qui peuvent ensuite être sauvegardés dans un fichier. Nous l'avons implémenter après la création des vues pour que nous puissions tester à la fois le bon fonctionnement de celles-ci, et en plus d'avoir un retour concret sur les objets sérilisé. Par la suite, nous avons étés amenés à modifier les objets sérilisés pour ajouter des fonctionnalités ou déboguer par exemple. Et nous nous sommes alors rendu compte d'un problème, les objets sérilisés ne sont pas **rétro-compatibles**. Cela signifie que si nous modifions un objet sérilisé, et que nous essayons de le désériliser, nous aurons une erreur. Après des recherches sur internet, nous nous sommes rendu compte que la *JVM* crée "d'elle même" un *serialVersionUID* pour chaque objet sérilisé. Ce *serialVersionUID* est unique pour chaque objet, et permet de vérifier si l'objet sérilisé est le même que celui qui est désérilisé. Si ce n'est pas le cas, alors nous avons une erreur. Ce *serialVersionUID* est généré automatiquement par la *JVM* en fonction de ses membres et de leurs types, des interfaces implémentés, des méthodes, etc. Il est donc différent à chaque fois que nous modifions l'objet même de manière infime en rajoutant un seul attribut par exemple. Nous avons donc eu à plusieurs reprises des problèmes dès que nous chagions une classe modèle. Nous avons donc décidé de créer nous même le *serialVersionUID* de chaque classe modèle.

### Participants
Quand vous lancerez l'application, une fenêtre de connexion s'ouvrira. Vous pourrez soit vous **connecter**, soit vous **inscrire**. Si vous n'avez pas encore de compte, incrivez vous en remplissant les champs qui vous seront demandés. Une fois inscrit, vous aurez votre propre workspace qui sera automatiquement créé. Si vous avez déjà un compte, vous pourrez vous connectez en remplissant votre addresse mail et votre mot de passe.
Quand vous ajouterez un participant à un workspace, si celui-ci a déjà un compte de créé, il sera ajouté en fonction de son addresse mail uniquement. Sinon, il sera créé avec toutes les informations données, y compris son mot de passe.

### Rôles
Chaque utilisateur de l'application à un rôle attitré. Il y a trois rôles différents : **Admin**, **Member** et **Observer**. Chaque rôle a des droits différents. Chacun de ces roles a des permissions différentes, et peut donc modifer plus ou moins les workspaces.
Un **Admin** a tous les droits sur le workspace : il peut modifier entierement l'espace en le renommant, en ajoutant des membres, en ajoutant des boards, en modifiant des cartes, etc.
Un **Member** peut modifier les boards en y ajoutant des card lists, en changeant le nom des boards, ou en modifiant les cartes.
Un **Observer** n'a aucun *pouvoir* sur les workspaces : il ne peut rien modifier, ajouter, ou supprimer.
Le rôle de chaque participant est unique, et propre à chaque workspace. C'est à dire qu'une personne *A* peut être **Admin** sur un workspace *1*, mais un simple **Oberserver** sur un workspace *2*. Cela permet une meilleur gestion des permissions de tous en fonction des projets et des compétences de chacun. De plus, quand un Participant crée un nouveau Workspace, celui-ci est automatiquement ajouté en tant que créateur, pour par la suite mettre en place ce workspace, et y ajouter les bons membres. Par ailleur, tout le mode peut créer un workspace, même les **observer**. Cela n'aurait aucun sens de bloquer une personne de créer son propre workspace.

### Pistes d'amélioration possibles
Après de longues heures d'études, de gestion, et de **code particulierement**, nous avons réussi à obtenir une application de type Trello fonctionnelle et nous sommes réellement **statifait** du travail que nous avons fournis. Cependant, nous sommes conscients que notre projet n'est pas parfait en tout points, et nous avons même quelques idées pour **améliorer** notre application.
- Une première idée serait premièrement **d'améliorer le design** de notre application, notamment celle de nos combobox que nous avons beaucoup utilisés. Ces combobox sont plus simples à coder et à afficher car elles sontun seul bloc, mais cela peut parfois ête compliqué à naviguer.
- Une deuxième idée serait de pouvoir **archiver les cartes** et listes de cartes. Cela permettrait de pouvoir retrouver les anciennes cartes et listes qui ont déja été terminés pour peut-être recuperer des informations, ou annuler la validation par exemples.
- Une troisième idéé pourrait être **d'optimiser l'application** en fonction du systeme d'exploitation de la machine qui éxecute l'application. Par exemple, nous avons notre fenêtre de connexion qui fonctionne correctement sur MacOs, mais malheuresement celle-ci ne s'affiche pas correctement sur Windows. 
- Une quatrième idée serait de **gérer les informations** des participants differement. Premierement, nous pourrions vérifier que l'addresse mail est bien valide en regardant sa structure, ou en envoyant un mail de vérification. Deuxièmement, nous pourrions demander des caractères spéciaux pour les mot de passes. Troisièmement, Pour ces même mot de passe, nous avons la possibilités de les transformer en hash pour une meilleur sécurité de l'utilisateur.

### Package
Pour les fichiers de notre application, nous avons cette arborescence :
```
Trello-Lite
├───rapport.md
├───README.md
├───data
|   └───datas.ser
├───src
|   └───trelollite
│   	├───controller
│   	├───model
│   	├───style
│   	├───view
|       └───TrelloMain.java
```
Nous avons choisi de mettre le fichier *TrelloMain.java* à l'interieur de *src* car nous utilisons des données de ce fichier à travers une grande partie de nos autres fichiers. Il est donc logique d'avoir ce fichier à l'interieur du *package trelollite*, sinon, nous n'aurions pas accés aussi facilement à ces données.

### rapport
Avec ce projet, il y aura un rapport en deux formats différens. Un en *.md* et un en *PDF*. Celui en *.md* sera disponible directement dans le package du projet, et celui en *PDF* sera dans le fichier de rendu du projet. Nous avons choisi de faire deux formats différents pour que chacun puisse choisir le format qu'il préfère. Le format *.md* est plus simple à modifier directement durant le codage du projet, et le format *PDF* est plus simple à lire et à comprendre. De plus, nous plubierons certainement notre projet sur GitHub, et le format *.md* est plus simple à afficher sur cette plateforme.