Chess
===========

Chess has been considered for many years a **"standard" A.I. application**. Our application:

* Contains all the basic functionality of a chess game (board, pieces, rules).
* The implemented **A.I. engine** is a gready one: it loves capturing (and not giving up) material!
* **Alpha-beta pruning** is implemented, in order to speed-up searching.
* The engine analyzes to a fixed-depth which can be edited (variable *DEPTH_OF_SEARCH* in *MyChessGame.java*).

The classes in the *tests* package can be used in order to:

1. **Play against the A.I. engine** by using a text-based interface (*PlayGame.java*).
2. **Evaluate a position** which must be hard-coded as *input* (*Test_Positions_X.java*). +++