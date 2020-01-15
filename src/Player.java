import java.util.ArrayList;
import java.util.Scanner;

public class Player implements IPlayer{
    ArrayList<Integer> attackCoordList = new ArrayList<Integer>();


    int playerLives = lifeCounter;
    Scanner scan = new Scanner(System.in);

    @Override
    public boolean inPlay() {
        return false;
    }

    @Override
    public ArrayList<Integer> attackCoords() {
        return attackCoordList;
    }
}
