package tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BoardTest {

    private Board board;

    @Before
    public void before(){
        this.board = new Board();
    }

    @Test
    public void test_makeMove_returns_correct_board(){
        this.board.makeMove(0,0, Player.PLAYER_1);
        assertEquals(Player.PLAYER_1, this.board.getBoardForTesting()[0][0]);
    }

    @Test
    public void test_makeMove_throws_error_if_user_makes_move_in_an_occupied_position(){
        this.board.makeMove(0,0, Player.PLAYER_1);
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.board.makeMove(0,0, Player.PLAYER_1);
                }
        );
        assertEquals("That position is already occupied.", e.getMessage());
    }

    @Test
    public void test_makeMove_throws_error_if_user_makes_move_when_a_player_has_already_won(){
        this.board.makeMove(0,0, Player.PLAYER_1);
        this.board.makeMove(0,1, Player.PLAYER_1);
        this.board.makeMove(0,2, Player.PLAYER_1);
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.board.makeMove(0,2, Player.PLAYER_2);
                }
        );
        assertEquals("PLAYER_1 has already won", e.getMessage());
    }

    @Test
    public void test_makeMove_does_not_throw_an_error_if_user_makes_a_valid_move(){
        this.board.makeMove(0,0, Player.PLAYER_1);
        this.board.makeMove(0,1, Player.PLAYER_1);
        assertDoesNotThrow(
                () -> {
                    this.board.makeMove(0,2, Player.PLAYER_2);
                }
        );

    }

    @Test
    public void test_board_returns_NONE_with_empty_board() {
        Player winner = this.board.getWinner();
        assertEquals(Player.NONE, winner);
    }

    @Test
    public void test_board_returns_Player_1_when_Player_1_wins_via_top_row() {
        for (int i = 0; i < 3 ; i++) {
            this.board.makeMove(0,i, Player.PLAYER_1);
        }
        Player winner = this.board.getWinner();
        assertEquals(Player.PLAYER_1, winner);
    }

    @Test
    public void test_board_returns_Player_1_when_Player_1_wins_via_middle_row() {
        for (int i = 0; i < 3 ; i++) {
            this.board.makeMove(1,i, Player.PLAYER_1);
        }
        Player winner = this.board.getWinner();
        assertEquals(Player.PLAYER_1, winner);
    }

    @Test
    public void test_board_returns_Player_1_when_Player_1_wins_via_bottom_row() {
        for (int i = 0; i < 3 ; i++) {
            this.board.makeMove(2,i, Player.PLAYER_1);
        }
        Player winner = this.board.getWinner();
        assertEquals(Player.PLAYER_1, winner);
    }

    @Test
    public void test_board_returns_Player_1_when_Player_1_wins_diagonally_from_top_left() {
        for (int i = 0; i < 3 ; i++) {
            this.board.makeMove(i,i, Player.PLAYER_1);
        }
        Player winner = this.board.getWinner();
        assertEquals(Player.PLAYER_1, winner);
    }

    @Test
    public void test_board_returns_Player_1_when_Player_1_wins_diagonally_from_bottom_left() {
        for (int i = 0; i < 3 ; i++) {
            this.board.makeMove(2-i,i, Player.PLAYER_1);
        }
        Player winner = this.board.getWinner();
        assertEquals(Player.PLAYER_1, winner);
    }

    @Test
    public void test_board_returns_NONE_when_there_is_a_draw() {
        this.board.makeMove(0,0, Player.PLAYER_1);
        this.board.makeMove(0,1, Player.PLAYER_1);
        this.board.makeMove(0,2, Player.PLAYER_2);
        this.board.makeMove(1,0, Player.PLAYER_2);
        this.board.makeMove(1,1, Player.PLAYER_1);
        this.board.makeMove(1,2, Player.PLAYER_1);
        this.board.makeMove(2,0, Player.PLAYER_1);
        this.board.makeMove(2,1, Player.PLAYER_2);
        this.board.makeMove(2,2, Player.PLAYER_2);
        Player winner = this.board.getWinner();
        assertEquals(Player.NONE, winner);
    }
}
