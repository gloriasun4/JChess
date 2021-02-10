package com.chess.pgn;

import com.chess.engine.BoardUtils;
import com.chess.engine.board.Board;
import com.chess.engine.pieces.Pawn;

import java.util.Locale;

public class FenUtilities {

    private FenUtilities() {
        throw new RuntimeException("not instantiable!");
    }

    public static Board createGameFromFEN(final String fenString) {
        return null;
    }

    public static String createFENFromGAme(final Board board) {
        return calculateBoardText(board) + " " +
                calculateCurrentPlayerText(board) + " " +
                calculateCastleText(board) + " " +
                calculateEnPassantSquare(board) + " " +
                //find way to deal with clocks?
                "0 1";
    }

    private static String calculateBoardText(final Board board) {
        final StringBuilder builder = new StringBuilder();
        int count = 0;
        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            if (i%8 == 0) {
                builder.append("/");
            }
        }
        String result = builder.toString();
        return result;
    }

    private static String calculateEnPassantSquare(final Board board) {
        final Pawn enPassantPawn = board.getEnPassantPawn();
        if(enPassantPawn != null) {
            return BoardUtils.getPositionAtCoordinate(enPassantPawn.getPiecePosition() + 8 *
                    enPassantPawn.getPieceAlliance().getDirection());
        }
        return "-";
    }

    private static String calculateCastleText(final Board board) {
        final StringBuilder builder = new StringBuilder();
        if(board.whitePlayer().isKingSideCastleCapable()) {
            builder.append("K");
        }
        if(board.whitePlayer().isQueenSideCastleCapable()) {
            builder.append("Q");
        }
        if(board.blackPlayer().isKingSideCastleCapable()) {
            builder.append("k");
        }
        if(board.blackPlayer().isQueenSideCastleCapable()) {
            builder.append("q");
        }
        String result = builder.toString();
        return result.isEmpty() ? "-" : result;
    }

    private static String calculateCurrentPlayerText(final Board board) {
        return board.currentPlayer().toString().substring(0,1).toLowerCase();
    }

}
