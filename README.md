Reinforcement Learning - Adaptive Tic Tac Toe Game Engine
==========================================================

This toy project is about Machine Learning, in particular - Reinforcement Learning.

As practice is the best way to learn anything new, objective here is to play with basic RL concept - Markov decision process (MDP).

This project has two components - game-engine and game-ui.

game-engine
====================
 It provides services to model MDP for predicting next game state for any given game state.
 It keeps a history of game moves which resulted in losses. As history gets richer, decision making becomes wiser, predicting actions which are less likely to result in game loss by game engine.
 Services are written in Scala, Akka-Http and built using SBT. To start game-engine on port 8080, use command - sbt run

game-ui
====================
 It provides web interface to play against game-engine. Built in Angular, it assumes game-engine is running on localhost at port 8080.
 To build and start UI server in dev mode, use command - ng serve and point browser to http://localhost:4200

To test, restart the server and start new game. As server has just started, it hasn't got any history and so reward function will be quite weak.
In this state, game engine could be easily challenged and user can win easily. Then start new game and follow exactly same pattern of moves as before.
Now game engine will take some alternative move, as it now knows that previous sequence resulted in loss.
