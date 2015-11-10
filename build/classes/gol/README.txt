//******************************************************************************
//	Création d'un nouvel automate
//******************************************************************************

0) Créer une class représentant les état possibles en faisait implements State
1) Créer un nouveau type de cellule qui extends Cellule
2) Implementer les différentes fonctions requises
3) Dans Board.java completer initRandom() pour initialiser en random votre type 
	de Cellule (typiqument en la rendant en vie si <proba)
4) Dans Board.java completer initBoard() en utilisant Utils.createNewCell(VOTRE_ETAT_MORT)
5) Du coup dans Utils.java dans createNewCell() instancier cell avec votre nouveau type
	de cellule et d'état
6) Ajouter les boutons dans le FXML et les actions dans le controller pour pouvoir
	activer ce nouvel automate. Ne pas oublier de desactiver les autre modes quand
	on change d'automate. NOPE ON VA FAIRE UNE CHOICEBOX