import java.util.ArrayList;
import java.util.Scanner;

public class Player implements IPlayer{
    ArrayList<Integer> attackCoordList = new ArrayList<Integer>();
    int playerLives = lifeCounter;

    @Override
    public boolean inPlay() {
        return playerLives > 0;
    }

    @Override
    public ArrayList<Integer> attackCoords() {
        return attackCoordList;
    }
}
