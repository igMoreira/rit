import sys

pos = 0
player = 1
win = False
draw = False
board = [ "\0","\0","\0","\0","\0","\0","\0","\0","\0" ]
lastmoves = []
count = 0

def getRealPlayerMove():
    status = True
    while status:
        print "\nPlease type the position you wish to play : "
        pos = int(raw_input())
        if board[pos] == "\0":
            status = False
        else:
            print "This place alredy contains a piece"
    return pos

def makeMove(board, pos):
    global player
    if player == 1:
        board[pos] = "X"
    else:
        board[pos] = "O"
    nextPlayer()
    lastmoves.append(pos)

def nextPlayer():
    global player
    player = (player % 2) + 1


def undo():
    board[lastmoves.pop()] = "\0"
    global win
    win = False

        
def realPlayerPlay():
    global board
    status = True
    while status:
        pos = getRealPlayerMove()
        if board[pos] != "\0":
            status = False
        else:
            makeMove(board,pos)
            status = False


def drawBoard():
    global board
    boardLimit = ""
    for i in range(len(board)):
        if (i == 0) or (i == 3) or (i == 6):
            boardLimit = ""
        else:
            boardLimit = "|"
        sys.stdout.write(boardLimit + " " + board[i] + " ")
        if (i == 2) or (i == 5):
            print "\n____________"


def virtualPlayerPlay():
    v = minimax_search(board)
    global pos
    v = alfa_beta_search(board)
    ##print "The value of V is " + str(pos)
    if player==1:
        nextPlayer()
    makeMove(board,pos)
    global win
    win = False
    global draw
    draw = False
    


############################### MINIMAX ##################

def minimax_search(board):
    global count
    v = min_value(board)
    print "Number of search trees Minimax: " + str(count)
    print "Position choosed with MiniMax : " + str(pos)
    count = 0
    return v

def max_value(game):
    global pos
    global count
    count = count + 1
    if endMatch(game):
        return evaluateState()
    v = float("-inf")
    for i in getPossibleMoves(game):
        global pos
        pos = i
        makeMove(game,i)
        v = max(v,min_value(game))
        undo()
    return v

def min_value(game):
    global pos
    global count
    count = count + 1
    if endMatch(game):
        return evaluateState()
    v = float("+inf")
    for i in getPossibleMoves(game):
        global pos
        pos = i
        makeMove(game,i)
        v = min(v,max_value(game))
        undo()
    return v

####################################################################

############################### ALFA BETA PRUNING ##################

def alfa_beta_search(board):
    global count
    v = max_value_pruning(board, float("-inf"), float("inf"))
    print "Number of search trees Alfa Beta Prunnig: " + str(count)
    print "Position choosed with Alfa Beta Prunnig : " + str(pos)
    count = 0
    return v

def max_value_pruning(game, alfa, beta):
    global pos
    global count
    count = count + 1
    if endMatch(game):
        return evaluateState()
    v = float("-inf")
    for i in getPossibleMoves(game):
        global pos
        pos = i
        makeMove(game,i)
        v = max(v,min_value_pruning(game,alfa,beta))
        undo()
        if v >= beta:
            return v
        alfa = max(alfa,v)
    return v

def min_value_pruning(game, alfa, beta):
    global pos
    global count
    count = count + 1
    if endMatch(game):
        return evaluateState()
    v = float("+inf")
    for i in getPossibleMoves(game):
        global pos
        pos = i
        makeMove(game,i)
        v = min(v,max_value_pruning(game,alfa,beta))
        undo()
        if v <= alfa:
            return v
        beta = min(beta,v)
    return v

####################################################################
def evaluateState():
    global player
    if win:
        if player == 1:
            return 1
        if player == 2:
            return -1
    else:
        return 0

def getPossibleMoves(game):
    possibleMoves = []
    for i,v in enumerate(board):
        if v == "\0":
            possibleMoves.append(i)
    return possibleMoves

def hasWinner(board):
    status = False
    if checkRow(board):
        status = True
    if checkColummn(board):
        status = True
    if checkDiagonal(board):
        status = True
    return status

def checkRow(board):
    if (board[0] == board[1]) and (board[1] == board[2]) and (board[0] != "\0"):
        return True
    if (board[3] == board[4]) and (board[4] == board[5]) and (board[3] != "\0"):
        return True
    if (board[6] == board[7]) and (board[7] == board[8]) and (board[6] != "\0"):
        return True
    return False

def checkColummn(board):
    status = False
    if (board[0] == board[3]) and (board[3] == board[6]) and (board[0] != "\0"):
        return True
    if (board[1] == board[4]) and (board[4] == board[7]) and (board[1] != "\0"):
        return True
    if (board[2] == board[5]) and (board[5] == board[8]) and (board[2] != "\0"):
        return True
    return False

def checkDiagonal(board):
    if (board[0] == board[4]) and (board[4] == board[8]) and (board[0] != "\0"):
        return True
    if (board[2] == board[4]) and (board[4] == board[6]) and (board[2] != "\0"):
        return True
    else:
        return False

def endMatch(board):
    global win
    global draw
    if hasWinner(board):
        win = True
    elif hasDraw(board):
        draw = True
    return (win or draw)

def hasDraw(board):
    if "\0" not in board:
        return True
    return False

def startMatch():
    while not endMatch(board):
        realPlayerPlay()
        drawBoard()
        print "\n"
        virtualPlayerPlay()
        drawBoard()

def main():
    startMatch()

main()
