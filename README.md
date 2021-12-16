# Coding Week (2021-2022)

## Authors
Groupe 20  
BOULECHFAR Sami  
KHATIB Maha  
ABDOULHOUSSEN Hamza  


## Lancement du jar

Pour lancer le jar, vous pouvez exécuter le fichier `launch_jar.sh`
```
./launch_jar.sh
```

Sinon il est possible d'inserer le path vers les dépendances javafx et completer path
```
java --module-path $path --add-modules javafx.base,javafx.controls,javafx.fxml -jar Jar/MyRdv-1.0.jar
```

Par exemple :
```
java --module-path JavaFX/javafx-sdk-17.0.1/lib --add-modules javafx.base,javafx.controls,javafx.fxml -jar Jar/MyRdv-1.0.jar
```

## Creation de la base de donnée
Un dossier .MyRdv est créé à la racine  
On peut repartir de la base initiale en supprimant ce dossier  
Ce dossier permet de fermer l'application tout en ayant sauvegardé les modifications

Des modifications ont été effectuées sur la base de donnee. Il est préferable de supprimer le dossier .MyRdv de la racine  
Lors du lancement de l'application, le chemin vers la racine est affiché  

## Exemple utilisé
il est possible de voir les informations dans BaseDeDonnee/CodingWeek

Voici les exemples utilisés pour afficher des Rendez-Vous

### identifiant professeur
- nom : a,   mdp: b

### identifiant Etudiant
- nom : c,  mdp: d

### Si les exemple ne se lance pas
Il est possible que le jar ne charge pas correctement les informations de la base de donnees
Dans ce cas peut être lancer le main avec la commande

```
./gradlew run
```

## Fonctionnalité
### Page Acceuil
- Se connecter en tant que professeur
- Se connecter en tant qu'élève
- Connection admin  
Pour cela, il ne faut être ni etudiant ni professeur
  (appuyer sur le logo)
nom : admin et mdp : admin

### Page Professeur
Il peut afficher confirmer, annuler un rendez-vous
Changer son profil en appuyant sur l'image profil
Le logo permet de revenir aux pages précédente

### Page Eleve
Il peut afficher annuler un Rendez-Vous
Changer son profil en appuyant sur l'image profil
Demander un rendez-vous
Le logo permet de revenir aux pages précédente

### Page Admin
Ajouter un professeur  
Le logo permet de revenir aux pages précédente




## Realisation de test
Avec gradle en chargant la class main avec
```
eu.telecomnancy.javafx.Test
```

Les tests sont lancés, le résultat ressemble à 
```
Test1 : creationrdv
✅ okay

Test2 : creationrdv
✅ okay

Test3 : rdv est en attente
✅ okay

Test4 : rdv est confirmé
✅ okay

Test5 : rdv est annulé
✅ okay

Test6 : rdv est archivé
✅ okay
```



## Template Gradle JDK/JavaFX

Ajout du template du projet proposé [ici](https://gitlab.telecomnancy.univ-lorraine.fr/Gerald.Oster/boilerplate-gradle-jdk15) 

Il s'agit d'une version mise à jour du projet  [boilerplategradlejdk13](https://github.com/Typhon0/boilerplategradlejdk13) disponible sur GitHub

## RoadMap

### Tâches réalisées le 13/12/2021

- [x] Découverte du sujet / Brainstorming.

- [x] Conception des interfaces : PageAccueil, PageEleve, PageProf et PageReservation.

- [x] Implémentations en fxml des interfaces : PageAccueil, PageEleve, PageProf.

- [x] Dessin du diagramme de classe.

- [x] Début d’implémentation des classes.

- [x] Conception de la base de données.

- [x] Début de rédaction du cahier des charges. 


L'équipe a essayé de faire un jar mais n'a pas réussi.

### Tâches à réaliser pour le 14/12/2021 :
- 
- [x] Réalisation de la base de données
  - [x] Conception du schéma relationnel
  - [x] Creation de la base de donnée en fichier db

- 
- [x] Compléter la base avec des exemples :
    - [x] Ajout de professeurs
    - [x] Ajout d'eleves
    - [x] Ajout d'horaires

- 
- [x] Pour PageAcceuil
    - [x] onAction : Etudiant
    - [x] onAction : Eleve
    - [x] setErreur
    - [x] onAction Connexion

- 
- [x] Récuperer l'id du prof dans la bdd
  - [x] getProfIdFromDb

- 
- [x] Récuperer l'id de l'eleve dans la bdd
    - [x] getEleveIdFromDb
    
    
-  
- [x] Finir l’implémentation en fxml des interfaces
  - [x] PageAcceuil
  - [x] PageProf
  - [x] PageEleve
  - [x] PageDemandeRdv
-  
- [x] Commencer à implémenter des tests

- 
- [x] Mettre à jour le diagramme de classe
    - [x] Ajouter le modèle
    - [x] Ajouter les controleurs
        - [x] PageAcceuil
        - [x] PageProf
        - [x] PageEleve
    - [x] Ajouter les types des attributs
    - [x] Ajouter les visibilités des attributs et méthodes
 

- [ ] Chercher comment mettre une base sur serveur (H2) (annulée)

### Tâches à réaliser pour le 15/12/2021 :

-
- [x] Base de données :
    - [ ] Héberger la base sur un serveur (annulée)
    - [x] Réussir à accéder à certaines données de la base depuis les interfaces
    - [x] Réussir à modifier la base depuis les interfaces 

-
- [x] Implémenter les méthodes des interfaces :
    - [x] PageAcceuil : AfficherMdp
    - [x] PageEleve : RdvEnAttente, RdvConfirme, RdvArchive et update
    - [x] PageProf :  RdvEnAttente, RdvConfirme, RdvArchive et update
    - [x] PageDemandeRdv : DemandeRdv et update
    - [ ] Verifier les disponibilités des professeurs

-
- [x] Améliorer l'aspect graphique des interfaces

-
- [ ] Optimiser les tests
  - [ ] tester la classe Professeur
  - [ ] tester la classe Eleve
  - [ ] tenter d'utiliser Junits et faciliter le lancement des tests


### Tâches à réaliser pour le 16/12/2021 :
-
- [x] Ajouter une classe Admin
  - [x] L'Admin peut ajouter des professeurs
  
- 
- [ ] Tester la base de données

- 
- [ ] Interfaces : Aboutir à une version finale de l'aspect graphique 

- 
- [x] Réaliser le Logo de l'application

- 
- [ ] Gestion des disponibilités des professeurs

- 
- [x] Ajout des status dans la base de donnee
  - [x] Confirmer
  - [x] Annuler
  - [x] Archiver

- 
- [x] Modification Profil Prof

- 
- [x] Modification Profil Eleve


### Tâches à réaliser pour le 17/12/2021 :
-
- [ ] Réaliser la video affichant les fonctionnalités de l'application

- 
- [ ] Modification d'un rendez-vous

- 
- [ ] Ajouter plusieurs eleve à un rendez-vous

- 
- [ ] Admin peut supprimer des professeurs et créer/supprimer des professeurs
