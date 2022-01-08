# Membres de l'équipe
ALMEIDA Néo, LAGNEAU Simon, MACIEIRA Matteo, VANHEE Paul.

## Pour exécuter le projet :
Dans le dossier téléchargé :

Linux : `java -jar .\target\projetmode0-Livrable2.jar`
Windows : `java -jar target/projetmode0-Livrable2.jar`


# Livrable 1

## Fonctionnalités implémentées

- [x] Afficher la liste détaillée des modèles
- [x] Il est possible de choisir le modèle à visualiser dans la liste
- [x] Le modèle est chargé et visualisé
- [x] Message d'erreur en cas d'erreur de format dans le fichier
- [x] La visualisation est correcte (La visualisation centrale oui, les deux autres ont quelques erreurs)
- [x] On visualise simultanément trois vues du modèle (de face, de haut, de côté) (retiré au second rendu)
- [x] Rotation
- [x] Translation
- [x] Homotétie (L'échelle)

## Autres éléments demandés

- [x] Tests pour les classes de calcul mathématique
- [x] Captures d'écran pour le rendu
- [x] Vidéo de présentation du rendu
- [x] Respect du format de rendu (cf Moodle)

## Distribution du travail (qui a fait quoi)

ALMEIDA     Néo  (Ninhache/Néo Almeida)  :  Interface/Implémentation des 3 vues/Thèmes/Modif de classes/Javadoc <br>
LAGNEAU     Simon (Simon Lagneau)  :  PLYReader/Implémentation des 3 vues/Modif de classes/Javadoc/Tests <br>
MACIEIRA    Matteo (Matte/matteo.macieira.etu/Matteo Macieira) :  Rotation (Sliders/Spinners)/Fix Tests et classes/Implémentation des 3 vues/Modif de classes <br>
VANHEE      Paul (Extasio15/Paul Vanhee) :  API/Zoom/Calcul Matriciel (Rotation/Translation)/Modif de classes ?<br>

## Difficultés rencontrées

Simon : Maintenir le Reader à jour <br>
Néo : Respecter le MCV à 100% ! <br>
Matteo : Link les sliders sur toutes les vues <br>
Paul : Les calculs Matriciels <br>

# Livrable 2

## Fonctionnalités implémentées


- [x] Affichage faces seulement / segments seulement
- [x] Affichage avancé de la bibliothèque de modèles :
- [x] Recherche dans la bibliothèque de modèles
- [ ] Éditer les informations sur un modèle
- [x] Modèle centré
- [x] Éclairage
- [ ] Lissage
- [ ] Ombre portée
- [ ] Vue en tranches
- [x] Controleur horloge 
- [x] Autres, préciser :
    - Choix du thème de l'interface
    - API (Code source disponible), Si on souhaite l'utiliser à l'Université (ou autre), on à la possibilité de changer son proxy
    - Affichage Sommets
    - Couleurs des modèles modifiable (non permanent), Sommets, Arrêtes, Faces, Fonds
    - Ouvrir plusieurs modèles, via des onglets
    - Implémentation d'un algorithme pour optimiser le rendu (Similable à du RayCasting, mais fais sans Ray..)
    - Gestion dynamique des fichiers


## Autres exigences

- [x] Tests unitaires
- [x] Diagramme de classes UML
- [x] Javadoc
- [x] Captures d'écran
- [x] Vidéo de présentation
- [x] Respect du format de rendu

## Distribution du travail (qui a fait quoi)

ALMEIDA     Néo     :   Documentation, Calcul Matriciel, Observer/Observé, Affichage (Éclairage, algorithme du peintre, "RayCasting"), Interface, Librairie de fichiers avancée, Thèmes, Les fenêtres d'informations (+Gestion d'erreur), Gestion dynamique des fichiers, Clean Code, Exporter en png, Canvas "receptif" => On peut les bouger à la souris, Zoom à la souris, Raccourcis claviers <br>
LAGNEAU     Simon   :   Documentation, Reader, La barre de Recherche, Tests, Clean Code, <br>
MACIEIRA    Matteo  :   Documentation, Génération PMD + UML, La partie "Sliders" sur le côté (Slider de rotation + Bouton de reset), Controleur horloge, Librairie de fichiers avancée, Vidéo de présentation, Tentative d'Implémentation des 3 vues, Clean Code,  <br>
VANHEE      Paul    :   Documentation, Calcul Matriciel, Api, Bouton de zoom, , Tests Matriciels, Tentative d'Implémentation des 3 vues, Tentative d'Implémentation de l'Affichage en tranches, Clean Code <br>

## À Noter !

> Paul a participé activement au developpement du projet malgré le peu de push (sur git), de plus, de plus, les push de la dernière soirées ont tous été fait depuis la même machine, mais réalisé par plusieurs personnes (en utilisant CodeWithMe, extensions proposée par IntelliJ pour travailler en groupe, sur une même machine).

> Beaucoup de Push sur git ont dans leur nom ".class", c'est à cause du livrable1, qui nous à générer un nombre incalculable de .class qui venait se loger sur git (malgré le .gitignore).


## Difficultés rencontrées

Néo : Respecter le clean code était compliqué surtout que je pense que nous sommes partis sur une mauvaise base, non-utilisation d'enum ni d'interface, alors que sur la fin du projet, celle-ci semblaient nécessaires.
Matteo et Paul : les 3 vues sans re-générer un modèle pour éviter les trop nombreux calculs.
Simon : les "ByteArrayOutputStream".

