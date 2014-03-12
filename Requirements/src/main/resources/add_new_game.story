Narrative:
ADD NEW TITLE/GAME 
If an Xbox game is not currently in the list of games we want or own the user will have the ability to add that 
game to the list by providing the game’s title.  There will be no validation that the title is an actual  Xbox 
game but to keep the list clean duplicate titles will not be allowed.  Adding a new game will also automatically 
add the first vote on behalf of the user. 

Scenario:  an Xbox game is not currently in the list of games we want or own

Given an xbox game called "Paperboy wants his dollar"
And the game is not wanted
And the game is not owned
When the user adds the game
Then the game is added to the wanted to list
And the game  is not in the owned list
And a vote is registered for the user

Scenario: An xbox is currently on the list of games we want, and the user tries to add it.

Given an xbox game called "Paperboy wants his dollar"
And the game is in the wanted list
And the game is not in the owned list
When the user adds the game
Then the user told the game is already in the wanted list
And the game  is not in the owned list
And the game is in the wanted list
And a vote is not registered for the user

Scenario: An xbox game is currently on the list of games we own, and the user tries to add it

Given an xbox game called "Paperboy wants his dollar"
And the game is not in the wanted list
And the game is in the owned list
When the user adds the game
Then the user told the game is already owned
And the game  is in the owned list
And the game is not in the wanted list
And a vote is not registered for the user