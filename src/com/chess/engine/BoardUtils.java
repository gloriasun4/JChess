package com.chess.engine;
//what is a utility class?
public class BoardUtils {

    //DELETE
    public static final boolean[] FIRST_COLUMN = null;
    //public static final boolean[] FIRST_COLUMN = initColumn(0);

    /*private static boolean[] initColumn(int columnNumber) {

        final boolean[] column = new boolean[64];

    }*/

    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    public static boolean isValidTileCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 64;
    }
}
