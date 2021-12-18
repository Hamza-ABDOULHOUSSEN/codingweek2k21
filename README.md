# Coding Week (2021-2022)

## Authors
Groupe 20  
BOULECHFAR Sami  
KHATIB Maha  
ABDOULHOUSSEN Hamza  

## Video de presentation 
Ci dessous le lien pour acceder a la video de presentation de l application sur youtube :

https://l.facebook.com/l.php?u=https%3A%2F%2Fyoutu.be%2Fe75hhiztLKE%3Ffbclid%3DIwAR2lYtKBMd0TLg6IJzLYa7b2jwgJQTpCGyzNixjmugV48jeQmV4NQl1gGck&h=AT0YMTd66o1054dxm02K-HDq4GxXNTYaAlkM_pQCbD1N89KcEvl9TktPfQ4jTRltHuNuwktWfcdrVdqRHmSmDX1e37MAXlGd7pOvWMRpTG9LcWyuoBXCiAVCSqtG1ipoCiYoC6tTCTrvLdB3Q4zCpw

## Lancement du jar

Pour lancer le jar, vous pouvez exécuter le fichier `launch_jar.sh`
```
./launch_jar.sh
```

Sinon il est possible d'insérer le path vers les dépendances javafx et compléter path
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
- Se connecter en tant qu'admin  
Pour cela, il ne faut être ni etudiant ni professeur (appuyer sur le logo)
nom : admin et mdp : admin

### Page Professeur
L'utilisateur peut afficher ses rendez-vous, qu'ils soient en attente, confirmé ou archivé.
Pour chaque rendez-vous en attente, il peut les confirmer ou les annuler. 
Pour chaque rendez-vous confirmé, il peut les annuler.
Accéder à sa page profil en appuyant sur l'image profil. Cette page lui permet de changer les informations de son profil.
Accéder à sa page planning lui permettant de charger et de modifier son planning.
Le logo permet de revenir à la page d'accueil.

### Page Planning
L'utilisateur a accés à une gridpane affichant ses horaires. Si l'utilisateur a deja un rendez-vous ou a préciser qu'il n'était pas disponible sur une plage horaire, la partie correspondante dans la gridpane est recouvert de rouge. Sinon, cela signifie que l'utilisateur est disponible.
Il peut modifier ses disponibilités et indisponibilités en précisant des plages horaires. Pour cela il sélectionne les horaires de début et de fin. 

### Page Eleve
L'utilisateur peut afficher ses rendez-vous, qu'ils soient en attente, confirmé ou archivé.
Pour chaque rendez-vous en attente, il peut les modifier ou les annuler. Si l'utilisateur veut modifier son rendez-vous, il est redirigé vers la page EditRdv.
Pour chaque rendez-vous confirmé, il peut les annuler.
Accéder à sa page profil en appuyant sur l'image profil. Cette page lui permet de changer les informations de son profil.
Accéder à la DemandeRdv lui permettant de demander un rendez-vous.
Le logo permet de revenir à la page d'accueil.

### Page DemandeRdv
L'utilisateur sélectionne un professeur, un jour et un rendez-vous pour son rendez-vous. Sans ces informations, le rendez-vous ne peut être crée et un message d'erreur s'affiche. 
S'il le souhaite, il peut préciser un intitulé, un lieu et une description. 
Il peut aussi ajouter d'autre(s) élève(s) à son rendez-vous. 
Une fois terminé, l'utilisateur envoie sa demande de rendez-vous.

### Page EditRdv
L'utilisateur peut s'il le souhaite sélectionner un nouveau professeur, un nouveau jour et/ou un nouvel horaire pour son nouveau rendez-vous. 
Il peut également modifier l'intitulé, le lieu ou la description de son rendez-vous.

### Page Admin
L'utilisateur peut créer un compte professeur ou élève.  
Le logo permet de revenir à la page d'accueil.



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
- [x] Modification des rendez vous par l'élève 

-
- [x] Création du planning 
  - [x] Création de l'interface 
  - [x] Implémentations du controller
  - [x] Modification du modèle 

- 
- [x] Modification graphique des Interfaces

-
- [x] Finalisation du diagramme de classe

-
- [ ] Réalisation des diagrammes de séquence
  - [ ] Création d'un rendez-vous
  - [ ] Création d'un compte professeur
  - [ ] Accés au planning 

-
- [ ] Réaliser la video affichant les fonctionnalités de l'application

- 
- [ ] Ajouter plusieurs eleves à un rendez-vous

- 
- [ ] Admin peut supprimer des professeurs et créer/supprimer des professeurs
