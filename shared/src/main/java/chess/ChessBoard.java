package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
 ChessPiece [][] squares = new ChessPiece[8][8];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }

    public void clearBoard(){
        for (int row = 0; row<8; row++){
            for (int col = 0; col<8; col++){
                squares[row][col] = null;
            }
        }
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        clearBoard();
        // addPiece 1 to 9 remember
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChessBoard that)) {
            return false;
        }
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

    public boolean isEmpty(ChessPosition myPosition){
        return getPiece(myPosition) == null;
    }

    public boolean inBounds(ChessPosition myPosition){
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }
}
