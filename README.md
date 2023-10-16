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

## Modeling the different components of the game
![UML](https://svgshare.com/i/tf6.svg)

This UML diagram shows the model without the effects of the cards taken into consideration. It, of course, doesn't show the controller either.

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

**Cards**

The model for the different cards is the most complex part of the implementation, taken care of by a hierarchy made up of two traits, two abstract classes and four concrete classes.

The parent trait `ICard` represents all cards, and it's commonly used in methods and other constructors as the default type of the cards. The class `AbstractCard` directly implements `ICard` and it's used as a base for the implementation of the rest of the cards. Its constructor only requires a `name` string. Here, the hierarchy tree diverges into two branches:

- The `WeatherCard` class: directly inherits the constructor and methods from `AbstractCard`. Thus, weather cards require only a name to be identified.

- The `AbstractUnitClass` class: Aside from inheriting from `AbstractCard`, it implements the trait `IUnitCard`. Its constructor now requires a number of strength points as identifier, aside from a name.

- The `CloseCombat`, `Range` and `Siege` classes: Inherit from `AbstractUnitCard`. Each type belongs in a different zone in the board side, and therefore, each class implements the method `goToZone` specified in `IUnitCard` in their own way.

The trait `IUnitCard` is a way to make working with unit cards easier, by giving them an 'umbrella type' that excludes weather cards. For example, there could be a method from the board that receives or affects unit cards regardless of their specific type; in that case, it's much easier and better to just work with IUnitCard than making overloading methods for each specific type of unit card.

For the implementation of the effects, please go to the Effects section. 

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

# Effects

Possibly the most controversial part of the model. A Gwent card can have an effect that affects other cards when placed.
These are:

For unit cards
- Tight bond
- Morale boost

For weather cards

- Biting frost
- Impenetrable fog
- Torrential rain
- Clear weather

When planning their implementation, I had a strong vision of the way I wanted it to be. I had these principles in mind:

- The effects shouldn't depend on the type of the card that's being affected.


- An effect should be able to affect any row of unit cards.

For example, Tight Bond only affects the same row of the card that has the effect. By the principles I established, there should only be one TightBond() class, and not three (one for each type of card). Besides, a TightBond() object should allow the programmer to configure it. If I want a Siege card with a Tight bond effect that looks for name matches in the range card row, it should be possible to do without making a new class.  

With that in mind, I proceed to explain the implementation.

The `IEffect` trait represents a generic effect. The class `AbstractEffect` implements it. All effects inherit `AbstractEffect`. The class allows to set the card that has the effect, and set a row reference, in which the effect will be applied (more on this later).

I also made the `ModifySPeffect` class, which takes a function and a `applyToItSelf` boolean (meant to indicate that the card applies the effect to itself) as parameters.

**Row references**

In order to implement the effects the way I wanted, I couldn't use any of the design patterns we have seen in class. Therefore, I had to figure out a way to do it, and this is what I came up with: Row references.

A row reference is an object, which sole purpose is to check if a card corresponds with a certain type. There is one reference for each type of card, and they implement the `Ref` trait. They are not cards, and cannot be passed as such.

The objects are `CloseCombatRef`, `RangeRef` and `SiegeRef`. They have only one method: `typeCheck(C:IUnitCard)`. This method uses pattern matching to see if the type of `C` corresponds with the one set in the reference.

All effects can take a reference as parameter. The reference has the responsibility to check whether the target card will be affected or not. 

This, in the end, allows the addition of both, new effects and new types of unit card, very cheaply. The only cost comes when a new type of unit card is implemented, this being having to add a new ref object with his `typeCheck` method. 

**Effects Implemented**

`TightBondEffect` Inherits `ModifySPEffect`, with the function f(x) = 2x and `applyToItself` as `true`. It can be created without parameters (in which case, it will affect only the cards with the same type as the one who applies it) or taking a row reference.

Similarly, `MoraleBoost` inherits `ModifySpEffect` with f(x) = x+1 and `applyToItself` as `false`. It can be created without parameters (in which case, it will affect only the cards with the same type as the one who applies it) or taking a row reference.

`WeatherEffect` inherits `AbstractEffect`, it must take a reference row as parameter and can take an integer `n` as well. The effect overrides the `SP` of the cards associated with the reference and sets it to `n` (1 by default).

- `WeatherEffect(CloseCombatRef)` is biting frost.
- `WeatherEffect(RangeRef)` is impenetrable fog.
- `WeatherEffect(SiegeRef)` is torrential rain.

`ClearWeather` inherits `AbstractEffect` and does not take parameters, and it sets the `SP` of the cards back to normal (considering, of course, all the stacked SP they may have due to unit card effects).

Finally `NullEffect` is an effect that does nothing. Every card "without" an effect, in reality has a `NullEffect`.

The default constructor of the cards doesn't consider an effect and just creates a card with a null effect. If an effect is specified in the constructor, the `effect` variable of the card and `card` variable of the effect will change accordingly. 

The effects are applied using an observer pattern. The cards are observers and the board and board side in which they arrive, are the subjects.
When a unit card is played, first, the current weather effect is applied to it, and then all cards observing the board side are notified and updated accordingly. When a weather card is played, all cards on board are notified of the new effect in place.

# The Standard Deck

The `CardPool` class represents the standard deck designed for the game. It stores the information of 10 unique unit cards and 4 unique weather cards in 3-tuples of (name, SP, effect). Therefore, there are 14 tuples in total.

It also stores card factories for each type of card.

It has two maps: one has every tuple associated with their frequency in the deck, and the other has every tuple associated with the factory that will be in charge of creating the card.

In total, 36 cards can be created in a pool. Each player is meant to have their own `CardPool` object associated (this is achieved with the `copy` method in the class, through the `GameConfiguration` state of the controller).

`CardPool` has the method `addToDeck`, which takes an array (the deck) and a 3-tuple with the information of a card. It then selects the appropriate factory, creates the card, reduces its frequency by 1 and adds it to the array.

# Basic AI
We didn't have the time to make a proper AI for the CPU players. However, in order to test the game, a `BasicAI` object was created. It only implements an `apply(target:Player)` method, which makes the target play a random card.

# The Controller
## State Diagram

![State Diagram](https://drive.google.com/uc?id=14YHQbLWs6mjy_lhEKEO0fj2IJJu2p4io)

The controller keeps track of 5 things:

- Its current state
- The player associated with the user
- The active players in the game
- The passed players
- The defeated players

An observer pattern design was implemented to take care of the player-controller communication. The controller observes the players. When a player dies or passes, a `PlayerControllerNotification` is sent to the controller with the information of the player. When a player dies, the controller will put them in the list of defeated players, and when a player passes, it will put them in the list of passed players.

Regarding the states, the controller works as follows:

- `MainMenu`: the starting state of the game. The user can go to the game configuration screen with the `newGame()` method.


- `GameConfiguration`: the game configuration screen. Here, the user can select a name for their player, select the name for their enemies, or select a number of random enemies up until 5. They could also start a new game without configuring anything, in which case the name of the player will be randomly choosed and there will be only one random enemy. The decks of everyone are randomly created using `CardPool`. When the user decides to start the game, the turns will be decided and the state will pass all the information stored to the controller. Everyone will be active in the beginning.


- `Idle`: in this state, the user can choose to pass or select a card. When entering the `Idle` state, all CPU players before the user (following the turns established) will play a card (using `BasicAI`). If no card can be played, choosing to play a card will automatically go to the `Passed` state anyways.



- `CardSelection`: here, the user must choose a card from their deck to play. After the card is played, all the CPU players after the user (again, following the turns) will play a card (using `BasicAI`). Then, the controller will go back to `Idle`.


- `Passed`: here, the user cannot play. This state iterates through the `activePlayers` list (which no longer contains the user) until everyone has passed. Then goes to `EndOfRound`.


- `EndOfRound`: in this state, the controller can finish the round. When a round is finished, a winner is decided and everyone else takes damage. Depending on the outcome, two transitions are possible. If the user wins or loses, then the controller goes back to the main menu, resetting everything. If the player was not defeated nor won, then goes back to `Idle`, everyone takes three cards and a new round starts.