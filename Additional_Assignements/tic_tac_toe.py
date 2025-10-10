import math

# Print board
def print_board(board):
    for row in board:
        print(row)
    print()

# Check winner
def check_winner(board):
    for row in board:
        if row.count(row[0]) == 3 and row[0] != ' ':
            return row[0]
    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] != ' ':
            return board[0][col]
    if board[0][0] == board[1][1] == board[2][2] != ' ':
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != ' ':
        return board[0][2]
    return None

def empty_cells(board):
    return [(i, j) for i in range(3) for j in range(3) if board[i][j] == ' ']

def minimax(board, depth, isMaximizing):
    winner = check_winner(board)
    if winner == 'X': return 1
    if winner == 'O': return -1
    if not empty_cells(board): return 0

    if isMaximizing:
        bestScore = -math.inf
        for (i, j) in empty_cells(board):
            board[i][j] = 'X'
            score = minimax(board, depth + 1, False)
            board[i][j] = ' '
            bestScore = max(score, bestScore)
        return bestScore
    else:
        bestScore = math.inf
        for (i, j) in empty_cells(board):
            board[i][j] = 'O'
            score = minimax(board, depth + 1, True)
            board[i][j] = ' '
            bestScore = min(score, bestScore)
        return bestScore

def best_move(board):
    bestScore = -math.inf
    move = None
    for (i, j) in empty_cells(board):
        board[i][j] = 'X'
        score = minimax(board, 0, False)
        board[i][j] = ' '
        if score > bestScore:
            bestScore = score
            move = (i, j)
    return move

# Main game
board = [[' ' for _ in range(3)] for _ in range(3)]
print("Welcome to Tic-Tac-Toe! You are O, AI is X")

while True:
    print_board(board)
    if check_winner(board) or not empty_cells(board):
        break

    # Human move
    i = int(input("Enter row (0-2): "))
    j = int(input("Enter column (0-2): "))
    if board[i][j] == ' ':
        board[i][j] = 'O'
    else:
        print("Invalid move. Try again.")
        continue

    if check_winner(board) or not empty_cells(board):
        break

    # AI move
    move = best_move(board)
    board[move[0]][move[1]] = 'X'

print_board(board)
winner = check_winner(board)
if winner:
    print(f"Winner: {winner}")
else:
    print("It's a draw!")


give this in ipynb so that the working will be visible in github without running the app

