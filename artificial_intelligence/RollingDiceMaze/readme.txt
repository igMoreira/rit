
Application structure:	

	This application was designed using an object-oriented approach in some parts of the code, and for reasons of
organization was preferrable have the classes in different files, the main application is the file sdmaze.py, is
important metion that the files within the directory should not be moved as it is used as resource of the application.

How To run:

	Requiriments: to run this application you need to have installed the python package in your PC, otherwise it will
not run.

	1.Open the terminal
	2.Navigate to the application directory
	3.Execute the following command "python sdmaze.py [puzze name]"
P.S.: The puzzle name is mandatory for the application and will not work without one, bellow is an example of the command
using the puzzle 1.
	"python sdmaze.py Puzzle1.txt"


Interpreting the output:

	The application shows the result of three different used heuristics, depending on the puzzle the output may get extense,
the output will follow the configuration bellow.

	************** NAME OF THE USED HEURISTIC *********

	moves: (Number of moves in the given solution)
	nodes in frontier: (Number of states added to the frontier set)
	nodes in explored: (Number of states added to the explored set)

	Solution: (Will display all path from start to goal if there is a solution, otherwise it will display the word "Fail")

	This structure will be displayed to each used heuristic.

P.S.: The heuristic "ByTheAngle" doesn't contain a precise estimative cost, so in puzzle 5 the solution turns to be a little extense
and might take some seconds to be displayed, in some terminals, e.g. command prompt (Windows), it may limit the displayed content. A
solution is display the solution in a generated text file, in Windows you can use the command "python sdmaze.py Puzzle5.txt >> log.txt"