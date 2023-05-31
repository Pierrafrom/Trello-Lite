# Trello-Lite

## Structure du programme 

1 - Package principal : trellolite
        Main : Classe principale contenant la méthode main pour lancer l'application.

2 - Package trellolite.models : Ce package contiend les classes représentant les modèles de l' application.
       Board, Card, List, Participant, Role, Worskpace
    
3 - Package trellolite.views : Ce package contiend les classes représentant les vues de l' application.
        MainFrame : La fenêtre principale de l'application contenant les tableaux.
        BoardPanel : Un panneau affichant les listes et les cartes d'un tableau.
        ListPanel : Un panneau affichant les cartes d'une liste.
        CardPanel : Un panneau affichant les détails d'une carte.
        ...

4 - Package trellolite.controllers : Ce package contiend les classes de contrôle pour gérer les interactions entre les modèles et les vues.
        BoardController : Gère les actions liées aux tableaux.
        ListController : Gère les actions liées aux listes.
        CardController : Gère les actions liées aux cartes.
        ...

5 - Package trellolite.styles : ce package contient les composants graphiques normalisé et stylisés pour l'application
