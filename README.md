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

#### Execution Instructions
As there's no graphic interface nor nothing, there's no execution implemented.

#### Suppositions and Explanation
* ##### Model
There were defined Items classes and some Packages.
For the Items, an interface defines what is an item, and there's an abstract item too with common behavior.
There's other interface that defines what's a wearable item, with methods like isWearableByThief and so with all the character classes.
As enemies can't hold items, then wearable items don't have methods for them.

The model package has status effects package, character package and weapon package. Weapon package inherit directly from model.

* ##### Weapon
A weapon is an Item, so extends the previous defined abstract item, but is also a wearable item, so it implements that interface.
It's defined a weapon interface too, with the behavior that a weapon has to have. This structure was made with the assumption of there would exist
wearable items that are not weapon, items like helmets or stuff like that. That's why the interfaces wearable item and weapon are separate.

There's a Generic Weapon class, with all common behavior that weapons have, and implement both interfaces weapon and wearable item.
While it could be an abstract class, I decided to not making it abstract, because we could have an unknown weapon, for example broken sword,
and somehow in a future implementation change it to a sword. It could be simply an item, but it didn't seem like that for me.

The classes Axe, Bow, Knife, Staff and Sword extends from this Generic Weapon,
overriding the methods isWearableBy those classes that can equip that weapon.

* ##### Status Effects
An interface defines what is a status, and then the classes Burned, Paralyzed and Poisoned implements it.
The statuses have methods that receive a character and apply effects to them.
The statuses are developed thinking in that all characters have a list of statuses that have to apply.
Those statuses decide what to do once the character ask them to do something. 

* ##### Character
An interface defines what a character is, and then an abstract character is defined too.
Characters can wait, attack, receive damage, and other things. They have a list with statuses, and they can add and drop statuses from the list. 

Enemy extends from character, so it doesn't include other things that players can do with their characters.
The enemy has a weight, because they can't equip weapons.
It was made the assumption that characters can attack all kinds of characters, including allies.
The enemies have resistance 0 to magical attacks, but that information is stored like a normal character.
I know that if I won't be using resistance, I shouldn't being implementing it and it should be a particularity of player characters,
but I considered that it would be more extensible for possible magical enemies.
This was actually on purpose, because
--off topic-- I remember be very surprised when I played a Final Fantasy when I was a kid, and I was able to attack my allies,
but then an enemy send one of my characters to sleep (this can't happen in this final reality, unless I define a special enemy with that ability) and
I decided trying to attack my sleeping character instead of waiting that the enemy attacked him, or healing it with magic or an item. And it worked,
and I attacked weakly so it was kind of fast. So I wanted to include that characteristic. 

The Package player is defined here.

* ##### Player
Player Character extends from our abstract character, and includes things that enemies can't do like equip weapons or heal.
Player Character implements an interface also.
To equip weapons and attack, player characters have their own class/job, and they delegate those actions to it.
It was designed making the assumption that a player character is not their job, but they have one, that is why they don't inherit from Knight, from example.
When the player character wants to equip a weapon, then they ask their class/job to equip.
That was thought like a person only knows how to use a tool if they study and acquired a job that can use it.
To attack is the same, they attack by their job.

In this package there iss another package for those Player Character Classes (jobs)

* ##### Character Class
In this package there're all the classes/jobs that a player character can have (only one per character).
There's an interface to define what a character class is. There's an interface for magic classes too.
When player characters try to equip weapons, they send the weapon to their class/job and then is this class that equips it, but they use double dispatch.
The class/job ask the weapon if they can equip, and if the weapon says true, the class equip, not the player.
To attack, the player attacks by his class/job, and this class uses the equipped weapon to attack the other character.