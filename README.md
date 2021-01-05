Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---

### Execution Instructions
Execute de game compiling normally, the execution doesn't require additional parameters.
The game starts with determined player characters and random enemies.
The user can do actions when is the turn of a player character. The user can equip, wait or attack with a character.
The player characters starts with no weapon equipped, but the inventory have some weapons the player can choose to equip.
The player can choose attack other player characters if wanted.

Once the game starts, the user can use the arrow buttons displayed on the screen to move the cursor among the options
the application display. The user can use the keyboard left/right arrows to control the cursor too. The user can move the cursor
when it's the turn of a player character.

The user selects an option pressing the A-Button that is on the screen, or simply pressing SPACE or ENTER keys on the keyboard.
The user can go to the previous option menu in some phases, via pressing the B-Button on the screen, or the keys X or BACKSPACE on the keyboard.



### Suppositions and Explanation
#### Model
The model is divided in three packages:
* The character package
* The items package
* The status effects package

##### Items

There were defined what is an Item and a Wearable items, both with interface and abstract classes which defines them and
have the common behavior. What mades a item a wearable item are that they have methods like isWearableByThief and so with all the character classes.
As enemies can't hold items, then wearable items don't have methods for them.

###### Weapon
A weapon is an Item, so extends the previous defined abstract item, but is also a wearable item, so it implements that interface.
It's defined a weapon interface too, with the behavior that a weapon has to have. This structure was made with the assumption of there would exist
wearable items that are not weapon, items like helmets or stuff like that. That's why the interfaces wearable item and weapon are separate.

There's an AbstractWeapon class, with all common behavior that weapons have, and implement both interfaces weapon and wearable item.
The classes Axe, Bow, Knife, Staff and Sword extends from this AbstractWeapon,
overriding the methods isWearableBy those classes that can equip that weapon.

##### Status Effects
An interface defines what is a status, and then the classes Burned, Paralyzed and Poisoned implements it.
The statuses have methods that receive a character and apply effects to them.
The statuses are developed thinking in that all characters have a list of statuses that have to apply.
Those statuses decide what to do once the character ask them to do something. 

##### Character
An interface defines what a character is.
Characters can wait, attack, receive damage, and other things. They have a list with statuses, and they can add and drop statuses from the list.
There's an abstract character that have the common behavior for characters. There're two types of characters: Enemies and Player Characters.

Characters can attack each other, no matter what type they are, even if they are allies.
--off topic-- I remember be very surprised when I played a Final Fantasy when I was a kid, and I was able to attack my allies,
but then an enemy send one of my characters to sleep (this can't happen in this final reality, unless I define a special enemy with that ability) and
I decided trying to attack my sleeping character instead of waiting that the enemy attacked him, or healing it with magic or an item. And it worked,
and I attacked weakly, so it was kind of fast. So I wanted to include that characteristic.

###### Enemy
Enemy extends from character, so it doesn't include other things that players can do with their characters.
The enemy has a weight, because they can't equip weapons.
The enemies have resistance 0 to magical attacks, but that information is stored like a normal character.
I know that if I won't be using resistance, I shouldn't being implementing it and it should be a particularity of player characters,
but I considered that it would be more extensible for possible magical enemies.

* ###### Player
Player Character extends from our abstract character, and includes things that enemies can't do like equip weapons or heal.
Player Character implements an interface also.
To equip weapons and attack, player characters have their own class/job, and they delegate those actions to it.
It was designed making the assumption that a player character is not their job, but they have one, that is why they don't inherit from Knight, from example.
When the player character wants to equip a weapon, then they ask their class/job to equip.
That was thought like a person only knows how to use a tool if they study and acquired a job that can use it.
To attack is the same, they attack by their job.

In this package there is another package for those Player Character Classes (jobs)

* * ###### Character Class
In this package there are all the classes/jobs that a player character can have (only one per character).
There's an interface to define what a character class is. There's an interface for magic classes too.
When player characters try to equip weapons, they send the weapon to their class/job and then is this class that equips it, but they use double dispatch.
The class/job ask the weapon if they can equip, and if the weapon says true, the class equip, not the player.
To attack, the player attacks by his class/job, and this class uses the equipped weapon to attack the other character.

### Controller
In this package there is all the methods that the player from the real world will use to control the model.
It is defined the interaction between the player and the model.
The controller manages the flux of the program, using turns queues with wait time.
It manages the player inventory, using an external class Inventory, which has an interface that defines it.

The inventory can store multiple Items of different kinds or even the same item multiple times.
The controller can take a weapon from the inventory and equip it, for example, to a player character.
Then the amount of weapons of that kind decreased, because it was taken from the inventory.

The controller has Event Handlers, which listens to certain events for avoiding busy waiting.
Those handlers are in the package listeners.

The game has multiple phases which the controller uses to manage the game.
There's a waiting phase to wait a new character turn. This phase can change to an enemy phase, where the enemy attacks,
or a player phase, where the player decides what action to do. From this phase, the player can go to a weapon selection phase
if they want the character to equip something, a selection of the target to attack if they want the character to attack,
or simply ends the character's turn making the character wait. In a target to attack selection phase, the player decides
to which character the player character will attack. Once the character attacks or wait, the character ends its turn and
the game phase goes to the waiting phase again, until other character is ready to start its turn.

#### Listeners
This package has the Event Handlers that the controller uses to listen to certain events.
When a character ends its turn, they tell to the listener CharacterReadyInQueueHandler, which listens those events.
Then this listener handles that event doing something in the controller to let him know that a player is ready for his next turn.

When a character is defeated, a listener FallenCharacterHandler listens that event and let the controller know that a character was defeated.
The controller then checks if there are remaining characters to know if the battle is over.
When that is the case, other event is fired, and the EndGameHandler listens to it.
Then it lets the controller know that the game is over.

### GUI
The graphic interface shows the enemies displayed in a row at the top of the screen, while the player characters are
displayed in a row at the middle of the screen. There's a background image where these characters are shown, and below 
this background image is displayed the text information of the player characters. At the bottom of the window, the player
sees the information of the current game phase and its options/actions to choose. There's a cursor to help the user
know which option is he choosing.

The characters and the cursor are groups which have animations and image files. Characters also have text information
where their name and hp is displayed to give more information to the player. These groups are organized as sprites in their
own package "spritegroups".