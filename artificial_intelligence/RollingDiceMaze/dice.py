
class Dice:

    NORTH = None
    SOUTH = None
    EAST = None
    WEST = None
    TOP = None
    BOTTOM = None

##    def __init__(self):
##        self.NORTH = 2
##        self.SOUTH = 5
##        self.EAST = 3
##        self.WEST = 4
##        self.TOP = 1
##        self.BOTTOM = 6

    def __init__(self, TOP, NORTH, EAST):
        self.TOP = TOP
        self.NORTH = NORTH
        self.EAST = EAST
        self.WEST = 7 - EAST
        self.SOUTH = 7 - NORTH
        self.BOTTOM = 7 - TOP

##    def __init__(self, dice):
##        self.NORTH = dice.NORTH
##        self.SOUTH = dice.SOUTH
##        self.EAST = dice.EAST
##        self.WEST = dice.WEST
##        self.TOP = dice.TOP
##        self.BOTTOM = dice.BOTTOM

    def moveNorth(self):
        NORTH = self.TOP
        TOP = self.SOUTH
        EAST = self.EAST
        return Dice(TOP, NORTH, EAST)

    def moveEast(self):
        EAST = self.TOP
        TOP = self.WEST
        NORTH = self.NORTH
        return Dice(TOP, NORTH, EAST)

    def moveSouth(self):
        NORTH = self.BOTTOM
        TOP = self.NORTH
        EAST = self.EAST
        return Dice(TOP, NORTH, EAST)

    def moveWest(self):
        EAST = self.BOTTOM
        TOP = self.EAST
        NORTH = self.NORTH
        return Dice(TOP, NORTH, EAST)

    def isEquals(self, dice):
        status = False
        if self.NORTH == dice.NORTH:
            if self.EAST == dice.EAST:
                if self.TOP == dice.TOP:
                    status = True
        return status
        

        
