# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---
# EP6
The communication between Controller and player was tested in the "notifications" test package.

A small explanation:

The class `PlayerDefeated` is a notification that inherits from the trait `PlayerControllerNotification` trait in the "notifications" package. A player is required in the constructor of a notification. All notifications have the `open(C:Controller)` method, which is meant to be used by the controller to receive a series of instructions coded in the notification.

An Observer pattern design was implemented between the `Player` and `Controller` classes. The Controller observes the Players. Players take damage with the `takeDamage()` method, and whenever they run out of gems, the method sends a `PlayerDefeated` notification to the controller. The `update` method in Controller opens the notification and executes the instructions.