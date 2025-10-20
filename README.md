# 🎲 Console Board Games – Java

Un projet Java regroupant plusieurs jeux de plateau jouables dans la console, dont **TicTacToe**, **Connect4**, et **Gomoku**. Le projet met en œuvre une architecture orientée objet, une logique de jeu générique, et des design patterns (Visitor, Strategy) pour gérer les états des jeux.

## Objectif du projet

Créer une **collection de jeux de plateau** en Java console avec :

- Une structure orientée objet réutilisable pour différents jeux
- Un moteur de jeu générique avec gestion des états (continuer, victoire, match nul)
- Différents modes de jeu : humain vs humain, humain vs IA, IA vs IA
- Interface console simple et interactive

## Jeux inclus

| Jeu | Taille du plateau | Symbole des joueurs | Modes de jeu |
|-----|-----------------|------------------|--------------|
| TicTacToe | 3x3 | X / O | 2 joueurs, humain vs IA, IA vs IA |
| Connect4 | 6x7 | Rouge / Jaune | 2 joueurs |
| Gomoku | 15x15 | ● / ○ | 2 joueurs |

## Technologies utilisées

- Java 17+
- Programmation orientée objet (POO)
- Design patterns : **Visitor**, **Strategy**
- Console Java pour l’interface utilisateur

## Lancer le projet localement

### 1. Cloner le dépôt

```bash
git clone https://github.com/AlinaD-2912/Board_Games.git
cd Board_Games
```
### 2. Compiler le projet
```bash
javac Main.java
```
### 3. Démarrer le projet
```bash
java Main
```
