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

## Exemple utilisé
### identifiant professeur
- nom : Sami,   mdp: 2703
- nom : Maha,   mdp: 0112
- nom : Hamza,  mdp: 0608

### identifiant Etudiant
- nom : Quentin,  mdp: 0905
- nom : Isabelle,  mdp: 1810
- nom : Alois,  mdp: 2711
- nom : Flavien,  mdp: 0101


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
- [ ] Récuperer l'id du prof dans la bdd
  - [ ] getProfIdFromDb

- 
- [ ] Récuperer l'id de l'eleve dans la bdd
    - [ ] getEleveIdFromDb

- 
- [ ] Créer la class Gestionstage
    - [ ] Ecrire la méthode SetScene
    
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
 

- [ ] Chercher comment mettre une base sur serveur (H2)

### Tâches à réaliser pour le 15/12/2021 :

-
- [ ] Base de données :
    - [ ] Héberger la base sur un serveur
    - [ ] Réussir à accéder à certaines données de la base depuis les interfaces
    - [ ] Réussir à modifier la base depuis les interfaces 

-
- [ ] Implémenter les méthodes des interfaces :
    - [ ] PageAcceuil : AfficherMdp
    - [ ] PageEleve : RdvEnAttente, RdvConfirme, RdvArchive et update
    - [ ] PageProf :  RdvEnAttente, RdvConfirme, RdvArchive et update
    - [ ] PageDemandeRdv : DemandeRdv et update

-
- [ ] Améliorer l'aspect graphique des interfaces

-
- [ ] Optimiser les tests
  - [ ] tester la classe Professeur
  - [ ] tester la classe Eleve
  - [ ] tenter d'utiliser Junits et faciliter le lancement des tests


### Tâches à réaliser pour le 16/12/2021 :
-
- [ ] Ajouter une classe Admin
  - [ ] L'Admin peut ajouter des professeurs

- 
- [ ] Fonctionnalités à rajouter :
    - [ ] Suite à une demande de Rdv, envoie de notification à l'enseignant

-
- [ ] Tester la base de données

-
- [ ] Interfaces : Aboutir à une version finale de l'aspect graphique 

-
- [ ] Réaliser le Logo de l'application

### Tâches à réaliser pour le 17/12/2021 :
-
- [ ] Réaliser la video affichant les fonctionnalités de l'application
