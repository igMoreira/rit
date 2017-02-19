from __future__ import division
import math

class Heuristic:

    def __init__(self):
        pass
    
    def manhattan_distance(self, currentState, goalState):
        return abs(currentState.location.x - goalState.location.x) + abs(currentState.location.y - goalState.location.y)

    def euclideanDistance(self, currentState, goalState):
        return ((currentState.location.x - goalState.location.x) ** 2 + (currentState.location.y - goalState.location.y) ** 2) ** 0.5

    def byTheAngle(self, currentState, goalState):
        a = abs(currentState.location.y - goalState.location.y)
        b = abs(currentState.location.x - goalState.location.x)
        angle = math.atan2(a,b)
        if angle == 0:
            return a
        return a/math.sin(angle)
