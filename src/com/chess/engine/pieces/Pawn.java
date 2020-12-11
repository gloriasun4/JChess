package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.BoardUtils;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import static com.chess.engine.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    //private static final int[] CANDIDATE_MOVES = {-8, 8};

    Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
    /* assume = tiles 0-63 orientation is white on bottom, black on top
    if piece is white,
    legal moves = up (if not occupied) -8, add move of promotion on 1st row
    , up+left (-9) and up+right (-7) (only if occupied by enemy piece, create attack move)
    up 2 spaces (-16) if on 7th row


    if piece is black,
    legal moves = down (if not occupied) 8, add move of promotion on 8th row
    down+left (7) and down+right (9) (only if occupied by enemy piece, create attack move)
    down 2 spaces (16) if on 2nd row

    en passant??? only if last move was 2 spaces up, need way to track last move
     */

        final List<Move> legalMoves = new ArrayList<>();
        int candidateDestinationCoordinate = this.piecePosition;
        Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

        if (this.pieceAlliance == Alliance.WHITE) {

            candidateDestinationCoordinate = this.piecePosition - 8;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                        !candidateDestinationTile.isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }

            candidateDestinationCoordinate = this.piecePosition - 16;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                        BoardUtils.SEVENTH_ROW[this.piecePosition] && !candidateDestinationTile.isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }

            candidateDestinationCoordinate = this.piecePosition - 7;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    candidateDestinationTile.isTileOccupied() && this.pieceAlliance != pieceAlliance) {
                legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }

            candidateDestinationCoordinate = this.piecePosition - 9;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    candidateDestinationTile.isTileOccupied() && this.pieceAlliance != pieceAlliance) {
                legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }
        }
        else {
            candidateDestinationCoordinate = this.piecePosition + 8;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    !candidateDestinationTile.isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }

            candidateDestinationCoordinate = this.piecePosition + 16;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    BoardUtils.SECOND_ROW[this.piecePosition] && !candidateDestinationTile.isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }

            candidateDestinationCoordinate = this.piecePosition + 7;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    candidateDestinationTile.isTileOccupied() && this.pieceAlliance != pieceAlliance) {
                legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }

            candidateDestinationCoordinate = this.piecePosition + 9;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) &&
                    candidateDestinationTile.isTileOccupied() && this.pieceAlliance != pieceAlliance) {
                legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }
        }

        return ImmutableList.copyOf(legalMoves);

    }
}
