package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

//what is an interface?
public interface MoveStrategy {
    Move execute(Board board, int depth);
}
