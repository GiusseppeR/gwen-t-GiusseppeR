# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---
## Introduction

Gwent is a card game where two players raise armies in order to fight each other in the battlefield. Every deck of Gwent cards is made up of two type of cards:

- Unit cards: represent the units of the army
- Weather cards: represent the conditions of the battlefield

The board represents the battlefield, and it's divided by two zones, in which the armies of each player stand.

During the game, the players can strengthen their armies or modify the weather condition using the cards at hand. Alternatively they can also take more cards from their deck.

[Work in progress]

## Modeling the different components of the game
![UML](https://svgshare.com/i/tf6.svg)

**Player**

The players are represented by the `Player` class. The constructor requires each player to have a name and a deck of cards. The deck introduced will act as their "default" deck, which could be changed at anytime in between games (not implemented yet).

The players have the following state variables:

- `Gems`: Int. Equivalent of HP
- `Hand`: Array of cards
- `board`: Board object linked to the player. None by default
- `boardSide`: BoardSide object linked to the player. None by default

The methods of the `Player` class are shown in the `Iplayer` trait. Aside from providing access to the state and constructor variables, the methods implemented in the class take care of:

- The action of playing a card
- The action of taking one or many cards from the deck
- The action of shuffling the player's deck
- Setting a board
- Setting a board side

[Work in progress]

**Cards**

The model for the different cards is the most complex part of the implementation, taken care of by a hierarchy made up of two traits, two abstract classes and four concrete classes.

The parent trait `ICard` represents all cards, and it's commonly used in methods and other constructors as the default type of the cards. The class `AbstractCard` directly implements `ICard` and it's used as a base for the implementation of the rest of the cards. Its constructor only requires a `name` string. Here, the hierarchy tree diverges into two branches:

- The `WeatherCard` class: directly inherits the constructor and methods from `AbstractCard`. Thus, weather cards require only a name to be identified.

- The `AbstractUnitClass` class: Aside from inheriting from `AbstractCard`, it implements the trait `IUnitCard`. Its constructor now requires a number of strength points as identifier, aside from a name.

- The `CloseCombat`, `Range` and `Siege` classes: Inherit from `AbstractUnitCard`. Each type belongs in a different zone in the board side, and therefore, each class implements the method `goToZone` specified in `IUnitCard` in their own way.

The trait `IUnitCard` is a way to make working with unit cards easier, by giving them an 'umbrella type' that excludes weather cards. For example, there could be a method from the board that receives or affects unit cards regardless of their specific type; in that case, it's much easier and better to just work with IUnitCard than making overloading methods for each specific type of unit card.

[Work in progress]

**Board**

The board is modeled by two classes: `Board` and `BoardSide`, each one independent and unrelated (to some extent) to the other. Though not directly associated, they are mediated by players (see relations in UML diagram. Further explanation in "Modeling Interaction Between Classes").

The `Board` class has the following responsibilities:

- Introducing players and assigning them a board side
- Telling the winner of the round
- Receiving the weather card played
- Displaying the last weather card played

To achieve that, it uses two state variables: `weatherZone` (to store weather cards) and `playerList` (to store references to the players). The methods implemented revolve around those two variables for all purposes.

The `BoardSide` class, on the other hand, has as responsibilities:

- To receive unit cards played
- To display all unit cards played
- To count its total score

For that, it uses arrays to store each type of card it can receive, as well as one to keep track of all cards played.

## Modeling Interaction Between Classes

One of the main challenges of the project is to properly achieve the effect of a playing a card. This is, implementing the following process whenever a player decides to play a card:

- Identifying the type of card
- If it's a weather card, then placing it on the board
- If it's a unit card, then placing it on its specific zone in the board side
- Doing it efficiently, with good and extensible code

To explain how this was achieved, let's go back to the classes for a moment. The `Board` class can introduce players to the board and assign a board side to them with the method `addPlayer`, which works as follows:

- Receives the reference to a player and a name for the board side
- Checks for incompatibilities (Is the player already on the board? Is the board side taken?). If none found, then continues
- Sets the `board` variable of the player
- Creates a `BoardSide` object with the given name, and sets it as the `boardSide` of the player

This way, the player and the board associate with each other, and the player associates with its respective board side. The benefit of implementing it this way, is that a board can have as many players as one might want, each one with its own board side, and not restricted to only two.

Now that player, board and board side are linked, they can communicate with each other through the `playCard` method in `Player`. This method does the following:

- Receives a card chosen by the player
- Removes it from its hand
- Starts a command chain that ends up with the card in its place

Lets `C : ICard` be the card played. Using `playCard(C)` will call the method `C.sendCommand(Player)`. From here, there are two paths:

- `C` is a weather card. Since `C` knows its own type, doing `C.sendCommand(Player)` will call the `sendCommand` implementation in `WeatherCard`, which will then take the `Board` object linked to the player (`Player.getBoard()`) and call the `setWeatherCard(C)` method.

- `C` is a unit card (close combat, for example). Again, since `C` knows that it is a unit card, it will call the implementation of `sendCommand` in `AbstractUnitCard` (which is inherited to all its subclasses). This, will then take the `BoardSide` object `B` linked to the player (`B = Player.getBoardSide()`) and call the `C.goToZone(B)` method. Since `C` is a close combat card, it will call the implementation of `goToZone` in `CloseCombat`, which will finally append `C` to the `CCzone` in `B`.

As it shows, the key of the process (and of any interaction for that matter) is the link between player, board and board side. Another example is the `getWinner()` method from `Board`, which calls the `getPoints()` method from the board side of each player in the game, and through an algorithm, decides the winner of the round.

[Work in progress]