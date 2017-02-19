import sys
execfile("location.py")
execfile("dice.py")
execfile("state.py")
execfile("a_star_search.py")
execfile("heuristics.py")

def buildMaze(content):
    maze = []
    aux = list()
    for ch in content:
        if ch == "\n":
            maze.append(list(aux))
            aux = list()
        else:
            aux.append(ch)
    return maze

def getFile(puzzleName):
    fo = open(puzzleName,"r")
    content = fo.read()
    fo.close()
    return content

def main():
    if len(sys.argv) != 2:
        print "Invalid use of the application"
        print "The application must follow : python rollingDiceMaze.py Puzzle.txt"
        return
    else:
        puzzleName = str(sys.argv[1])
        content = getFile(puzzleName)
        maze = buildMaze(content)
        startLocation = None
        goalLocation = None
        for i in range(len(maze)):
            for j in range(len(maze[i])):
                if maze[i][j] == "S":
                    startLocation = Location(i,j)
                if maze[i][j] == "G":
                    goalLocation = Location(i,j)
        startState = State(startLocation, Dice(1,2,3))
        goalState = State(goalLocation, None)
        heuristics = Heuristic()
        
        ## The search with the first heuristic
        print "*******MANHATTAN DISTANCE************"
        print ""
        search = A_Star_Search(maze, heuristics.manhattan_distance)
        search.printMaze()
        result = search.execute(startState, goalState)
        print "Moves : " + str(search.numberMoves)
        print "Nodes in frontier: " + str(search.numberFrontier)
        print "Nodes in explored: " + str(search.numberExplored)
        if isinstance(result,list):
            for state in result:
                print state.getStateConfig()
        else:
            print result
        print ""

        ## The search with the second heuristic
        print "*******EUCLIDEAN DISTANCE************"
        print ""
        search = A_Star_Search(maze, heuristics.euclideanDistance)
        search.printMaze()
        result = search.execute(startState, goalState)
        print "Moves : " + str(search.numberMoves)
        print "Nodes in frontier: " + str(search.numberFrontier)
        print "Nodes in explored: " + str(search.numberExplored)
        if isinstance(result,list):
            for state in result:
                print state.getStateConfig()
        else:
            print result
        print ""
        
        ## The search with the third heuristic
        print "*******BY THE ANGLE************"
        print ""
        search.printMaze()
        search = A_Star_Search(maze, heuristics.byTheAngle)
        result = search.execute(startState, goalState)
        print "Moves : " + str(search.numberMoves)
        print "Nodes in frontier: " + str(search.numberFrontier)
        print "Nodes in explored: " + str(search.numberExplored)
        if isinstance(result,list):
            for state in result:
                print state.getStateConfig()
        else:
            print result
        print ""
        
main()
