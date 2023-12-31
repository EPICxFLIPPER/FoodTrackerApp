# Food Tracker

## Proposal

This is an application that will allow users to
easily know what items they need to shop for based
on meals they plan to eat. This is intended for
students who find checking for ingredients to be a 
hassle. This is interesting to me as I hate planning
to cook something only to realize I am missing an
ingredient. I want to remove this problem from my life.
The intended functionality of this project is for users
to be able to select a recipe from a list of recipies they
like to cook. The program will then check if the user has 
the necessary ingredients to make the meal, and
let them know what items they are missing, so that they can
effectively go shopping.

## Required Data
Users will need to enter:
- The items they initially have in their pantry
- Any recipes they might want to make.

## User Stories
- As a user, I want to be able to add new recipes, which consist of a list of ingredients to my cookbook.
- As a user, I want to be able to add ingredients to my pantry that I was not told to shop for.
- As a user, I want to be able to view a list of all the ingredient I need to shop for if I want to make a particular recipe.
- As a user, I want to be able to remove Items from my pantry that I have used.
- As a user, I want to be able to see if I can make a particular recipe with the ingreidents in my pantry.
- As a user, I want to have the option to save recipes and items that I have in my pantry for later use.
- As a user, I want to be able to load a save file of recipes and items in the pantry to continue use.



## Citations
- Json demo provided by the CPSC 210 course team.
- Jswing Tutorial by BroCode on YouTube
- Cute Burger Cartoon for Children Book, By OreNyee On AdobeStock

## Instructions For Grader
- There are multiple visual aspects, The side lists in the pantry/Recipe Screens, the burger image on the home screen, and the burger image on the desktop hotbar.
- Saving and loading can both be done at any time by pressing the respective buttons on the home screen.
- Navigate to different screens via the menu bar at the top of the project or by pressing Alt+ the desired mnemonic
- To Add ingredients to the pantry, type in the required fields and press add ingredient.
- To Remove ingredients from the pantry, type the name of the ingredient in the "Ingredient select" field and press remove.
- To Add a recipe to the cookbook, type the name of the recipe in the text feild next to the add recipe button and press the button.
- To add ingridients to the recipe, type the name of the reicpe in the recipe select field and then add the ingridient with the same process as the pantry, only on the recipe menu.
- Removing recipes is the same as the pantry screen.
- To see if you can cook/ the ingredients needed to cook a recipe, type the recipe in the recipe select field and press can cook? button.

## Phase 4:

### Task 2:
Mon Nov 27 15:09:42 PST 2023 <br>
Loaded CookBook from:./data/cookbook.json <br>
Mon Nov 27 15:11:12 PST 2023<br>
Saved CookBook to./data/cookbook.json<br>
Mon Nov 27 15:09:59 PST 2023<br>
Added Ingredient to Pantry :Cheese<br>
Mon Nov 27 15:10:21 PST 2023<br>
Added Ingredient to Pantry :Ham<br>
Mon Nov 27 15:10:27 PST 2023<br>
Removed Ingredient to Pantry :Cheese<br>
Mon Nov 27 15:10:41 PST 2023<br>
Added new Recipe :Cheese Recipe<br>

### Task 3:
Given more time, to improve my design, I would first implement the Observer
Pattern in my GUI. Currently, the side lists need to update when things are 
added to lists and this is controlled by my current classes, when it could
be done with the observer pattern. I would also use the Singleton pattern on
the cookbook class, as there only needs to be one, that is univeral to the
system, therefore it is a good place to implement it.

 