package tictactoe;

public class Board {

    private final Player[][] board;
    /*
        [Player, Player, Player],
        [Player, Player, Player],
        [Player, Player, Player]
     */

    public Board() {
        this.board = new Player[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = Player.NONE;
            }
        }
    }

    public void makeMove(int row, int col, Player player) {
        if (this.getWinner() != Player.NONE){
            throw new IllegalArgumentException(String.format("%s has already won", this.getWinner().toString()));
        }
        if (this.board[row][col] == Player.NONE) {
            this.board[row][col] = player;
        } else {
            throw new IllegalArgumentException("That position is already occupied.");
        }
    }

    public Player getWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] != Player.NONE && this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board[i][2]) {
                return this.board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (this.board[0][i] != Player.NONE && this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i]) {
                return this.board[0][i];
            }
        }

        // Check diagonals
        if (this.board[0][0] != Player.NONE && this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]) {
            return this.board[0][0];
        }
        if (this.board[0][2] != Player.NONE && this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
            return this.board[0][2];
        }

        // Check for a tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == Player.NONE) {
                    return Player.NONE;
                }
            }
        }

        // If we've reached this point, it's a tie
        return Player.NONE;
    }

    public void printBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public Player[][] getBoardForTesting(){
        return this.board;
    }

}
