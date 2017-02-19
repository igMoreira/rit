
class A_Star_Search:
    maze = None
    heuristic = None
    numberMoves = 0
    numberFrontier = 0
    numberExplored = 0
    
    def __init__(self, maze, heuristic):
        self.maze = maze
        self.heuristic = heuristic

    def printMaze(self):
        for i in range(len(self.maze)):
            for j in range(len(self.maze[i])):
                print self.maze[i][j],
            print "\n"
        
    def getLowest(self, openset, f_score):
        lowestValue = 0
        lowestState = None
        for state in openset:
            if  f_score[state] >= lowestValue:
                lowestValue = f_score[state]
                lowestState = state
        return lowestState

    def is_valid(self, state):
        return self.valid_space(state.location.x, state.location.y) and state.dice.TOP != 6

    def valid_space(self, x, y):
        """Tests whether the given x, y is in the puzzle space and not a '*'."""
        return (0 <= x < len(self.maze) and
            0 <= y < len(self.maze[0]) and
            self.maze[x][y] != '*')
    
    def neighbor_nodes(self, node):
        (u, n, e) = node.dice.TOP,node.dice.NORTH, node.dice.EAST
        return filter(self.is_valid, [
            State(Location(node.location.x, node.location.y + 1), node.dice.moveNorth()), # North
            State(Location(node.location.x, node.location.y - 1), node.dice.moveSouth()), # South
            State(Location(node.location.x + 1, node.location.y), node.dice.moveEast()), # East
            State(Location(node.location.x - 1, node.location.y), node.dice.moveWest()) # West
            ])

    def manhattan_distance(self, currentState, goalState):
        return abs(currentState.location.x - goalState.location.x) + abs(currentState.location.y - goalState.location.y)

    def isGoal(self, state, goalState):
        if state.location.isEquals(goalState.location):
            if state.dice.TOP == 1:
                return True
        return False

    def isExplored(self, node, explored):
        for state in explored:
            if node.isEquals(state):
                return True
        return False

    def execute(self, startState, goalState):
        explored = []  ## The set of nodes already evaluated.
        frontier = []    ## The set of tentative nodes to be evaluated, initially containing the start node
        solution = []    ## The map of navigated nodes.
        g_score = {}
        f_score = {}
        g_score[startState] = 0    ## Cost from start along best known path.
        ## Estimated total cost from start to goal through y.
        f_score[startState] = g_score[startState] + self.manhattan_distance(startState, goalState)
        frontier.append(startState)
        self.numberFrontier = self.numberFrontier + 1
        while frontier:
            current = self.getLowest(frontier, f_score) ##the node in openset having the lowest f_score[] value
            solution.append(current)
            self.numberMoves = self.numberMoves + 1
            frontier.remove(current)
            explored.append(current)
            self.numberExplored = self.numberExplored + 1
            if self.isGoal(current,goalState): #.isEquals(goalState):
                return explored
     
            for neighbor in self.neighbor_nodes(current):
                if self.isExplored(neighbor, explored):
                    continue
                tentative_g_score = g_score[current] + self.heuristic(current,neighbor)
     
                if neighbor not in frontier or tentative_g_score < g_score[neighbor]: 
                    ##came_from[neighbor] = current
                    g_score[neighbor] = tentative_g_score
                    f_score[neighbor] = g_score[neighbor] + self.manhattan_distance(startState, goalState)
                    if neighbor not in frontier:
                        frontier.append(neighbor)
                        self.numberFrontier = self.numberFrontier + 1
     
        return "Fail"
        
