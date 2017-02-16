Chess
===========

* Contains all the basic functionality of a chess game (board, pieces, rules).
* The implemented chess engine is a gready one: it loves capturing (and not giving up) material!
* AB-pruning is implemented, in order to speed-up searching.
* The engine analyzes to a fixed-depth (variable "DEPTH_OF_SEARCH" in "MyChessGame.java") which can be edited.

The java classes in the "tests" package can be used in order to:

1. Play a chess game against the engine, by using a console-based, simple GUI (PlayGame.java class).
2. Evaluate a position which must be hard-coded as "input" (Test_Positions_X.java).