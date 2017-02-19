
class State:
    
    dice = None
    location = None

    def __init__(self, location, dice):
        self.dice = dice
        self.location = location

    def getStateConfig(self):
        config = list()
        config = [[self.location.x, self.location.y], {"TOP":self.dice.TOP, "NORTH":self.dice.NORTH, "EAST":self.dice.EAST}]
        return config

    def isEquals(self, state):
        status = False
        if self.location.isEquals(state.location):
            if self.dice.isEquals(state.dice):
                status = True
        return status
        
