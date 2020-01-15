import java.util.ArrayList;

public interface IBoard {
    ArrayList<Integer> addBoatToBoard(int placementCoordinates);
    boolean boardIsBombed(int bombedCoordinate);
    void printBoardWithBoats();
    void printBoardWhenBombed(int bombedCoordinate, boolean hit);
}
