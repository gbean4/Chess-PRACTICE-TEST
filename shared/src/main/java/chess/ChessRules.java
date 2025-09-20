package chess;

import java.util.HashSet;

public record ChessRules(ChessBoard board) {

    public HashSet<ChessMove> getSlidingMoves(ChessPosition myPosition, int [][] directions){
        HashSet<ChessMove> moves = new HashSet<>();
        for (int [] direction : directions){
            int dRow = direction[0];
            int dCol = direction[1];
            ChessPosition current = myPosition;

            while (true){
                current= new ChessPosition(current.getRow()+dRow, current.getColumn()+dCol);
                if (!board.inBounds(current)) {
                    break;
                } else if (board.isEmpty(current)){
                    moves.add(new ChessMove(myPosition, current, null));
                    if (board.getPiece(myPosition).getPieceType()== ChessPiece.PieceType.KING ||
                            board.getPiece(myPosition).getPieceType()== ChessPiece.PieceType.KNIGHT){
                        break;
                    }
                } else if (board.getPiece(current).getTeamColor()!= board.getPiece(myPosition).getTeamColor()){
                    moves.add(new ChessMove(myPosition, current, null));
                    break;
                } else{
                    break;
                }
            }
        }
        return moves;
    }

    public HashSet<ChessMove> getPawnMoves(ChessPosition myPosition){
        HashSet<ChessMove> moves = new HashSet<>();
        return moves;

    }



    }
