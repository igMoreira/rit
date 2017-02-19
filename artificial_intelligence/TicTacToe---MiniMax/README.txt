HOW TO RUN THE APP:

	To run this app you will need to install the python package in your PC, as soon as you do that
and have the environment paths properly configured all you need to do is open your terminal and
type the following command

cd [PATH WHERE IS LOCATED THE APP IN YOUR PC]

after this execute the command

python ttt.py

P.S.: double click in the file works as well

Have fun!!!




HOW TO USE:

	The app will ask you for the coodinate of the square you wanna play the board has the following
coordinates:

 0 | 1 | 2 
____________
 3 | 4 | 5 
____________
 6 | 7 | 8 

	After do that the machine will place the other round, and again you will be asked to choose a location
and this goes on till the game finishs, the game finish with the following conditions, victory or a tie. When 
there is a winner or a tie the app doesn't alarm you about this it simply finish its execution. Bellow are some
match demonstrations using the app.


***************************************GAME 1****************************************************


Python 2.7.6 (default, Nov 10 2013, 19:24:24) [MSC v.1500 64 bit (AMD64)] on win32
Type "copyright", "credits" or "license()" for more information.
>>> ================================ RESTART ================================
>>> 

Please type the position you wish to play : 
0
 X |   |   
____________
   |   |   
____________
   |   |   

Number of search trees Minimax: 47
Position choosed with MiniMax : 8
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 8
 X |   |   
____________
   |   |   
____________
   |   | O 
Please type the position you wish to play : 
7
 X |   |   
____________
   |   |   
____________
   | X | O 

Number of search trees Minimax: 23
Position choosed with MiniMax : 6
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 6
 X |   |   
____________
   |   |   
____________
 O | X | O 
Please type the position you wish to play : 
4
 X |   |   
____________
   | X |   
____________
 O | X | O 

Number of search trees Minimax: 11
Position choosed with MiniMax : 5
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 5
 X |   |   
____________
   | X | O 
____________
 O | X | O 
Please type the position you wish to play : 
1
 X | X |   
____________
   | X | O 
____________
 O | X | O 

Number of search trees Minimax: 1
Position choosed with MiniMax : 5
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 5
 X | X |   
____________
   | X | O 
____________
 O | X | O 
>>> 

****************************************GAME 2***********************************************************


Python 2.7.6 (default, Nov 10 2013, 19:24:24) [MSC v.1500 64 bit (AMD64)] on win32
Type "copyright", "credits" or "license()" for more information.
>>> ================================ RESTART ================================
>>> 

Please type the position you wish to play : 
5
   |   |   
____________
   |   | X 
____________
   |   |   

Number of search trees Minimax: 37
Position choosed with MiniMax : 8
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 8
   |   |   
____________
   |   | X 
____________
   |   | O 
Please type the position you wish to play : 
1
   | X |   
____________
   |   | X 
____________
   |   | O 

Number of search trees Minimax: 67
Position choosed with MiniMax : 7
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 7
   | X |   
____________
   |   | X 
____________
   | O | O 
Please type the position you wish to play : 
6
   | X |   
____________
   |   | X 
____________
 X | O | O 

Number of search trees Minimax: 24
Position choosed with MiniMax : 4
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 4
   | X |   
____________
   | O | X 
____________
 X | O | O 
Please type the position you wish to play : 
0
 X | X |   
____________
   | O | X 
____________
 X | O | O 

Number of search trees Minimax: 5
Position choosed with MiniMax : 2
Number of search trees Alfa Beta Prunnig: 5
Position choosed with Alfa Beta Prunnig : 2
 X | X | O 
____________
   | O | X 
____________
 X | O | O 
Please type the position you wish to play : 
3
 X | X | O 
____________
 X | O | X 
____________
 X | O | O 

Number of search trees Minimax: 1
Position choosed with MiniMax : 2
Number of search trees Alfa Beta Prunnig: 1
Position choosed with Alfa Beta Prunnig : 2
 X | X | O 
____________
 X | O | X 
____________
 X | O | O 
>>> 
