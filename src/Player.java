import java.util.ArrayList;

public class Player implements IPlayer{
    ArrayList<Integer> attackedCoordinatesList = new ArrayList<Integer>();
    int boatsLeft = boats;

    @Override
    public boolean hasBoatsLeft() {
        return boatsLeft > 0;
    }

    @Override
    public ArrayList<Integer> coordinatesAttacked() {
        return attackedCoordinatesList;
    }
}
