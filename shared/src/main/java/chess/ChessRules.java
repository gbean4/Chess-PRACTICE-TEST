package chess;

import java.util.HashSet;

import static chess.ChessPiece.PieceType.*;

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

    public void getPawnPromotion(HashSet<ChessMove> moves, ChessPosition from, ChessPosition to, ChessGame.TeamColor color){
        ChessPiece pawn = board.getPiece(from);
        if (color== ChessGame.TeamColor.WHITE && to.getRow()== 8||
                color == ChessGame.TeamColor.BLACK && to.getRow()==1){
            for ( var promotion: new ChessPiece.PieceType[]{QUEEN, ROOK, BISHOP, KNIGHT}){
                moves.add(new ChessMove(from, to, promotion));
            }
        } else{
            moves.add(new ChessMove(from, to, null ));
        }
    }


    public HashSet<ChessMove> getPawnMoves(ChessPosition myPosition){
        HashSet<ChessMove> moves = new HashSet<>();
        ChessPiece pawn = board.getPiece(myPosition);
        int direction = (pawn.getTeamColor()== ChessGame.TeamColor.WHITE)? 1:-1;

        ChessPosition current = myPosition;
        ChessPosition oneForward = new ChessPosition(current.getRow() + direction, current.getColumn());
        if (board.inBounds(oneForward)){
            if (board.isEmpty(oneForward)){
                getPawnPromotion(moves, current, oneForward, pawn.getTeamColor());
                if (pawn.getTeamColor()== ChessGame.TeamColor.WHITE && current.getRow()==2 || pawn.getTeamColor()== ChessGame.TeamColor.BLACK && current.getRow()==7){
                    ChessPosition twoForward = new ChessPosition(current.getRow() + direction*2, current.getColumn());
                    if (board.isEmpty(twoForward)){
                        getPawnPromotion(moves, current, twoForward, pawn.getTeamColor());
                    }
                }
            }
        }
        int[][] diagonalDir = new int[][]{{direction, 1}, {direction, -1}};
        for (int [] diagonal: diagonalDir){
            ChessPosition oneDiagonal = new ChessPosition(current.getRow()+diagonal[0], current.getColumn()+diagonal[1]);
            if (board.inBounds(oneDiagonal)){
                if (!board.isEmpty(oneDiagonal) && board.getPiece(oneDiagonal).getTeamColor()!= pawn.getTeamColor()){
                    getPawnPromotion(moves, current, oneDiagonal, pawn.getTeamColor());
                }
            }
        }
        return moves;

    }



    }
