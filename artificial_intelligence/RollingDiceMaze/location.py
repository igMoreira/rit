
class Location:
    
    x = None
    y = None

    def __init__(self, line, column):
        self.x = line
        self.y = column

    def isEquals(self, location):
        status = False
        if self.x == location.x:
            if self.y == location.y:
                status = True
        return status
