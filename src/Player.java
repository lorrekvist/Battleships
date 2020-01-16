import java.util.ArrayList;

public class Player implements IPlayer{
    private ArrayList<Integer> attackedCoordinatesList = new ArrayList<Integer>();
    private int boatsLeft = boats;

    @Override
    public boolean hasBoatsLeft() {
        return boatsLeft > 0;
    }

    @Override
    public ArrayList<Integer> coordinatesAttacked() {
        return attackedCoordinatesList;
    }

    public ArrayList<Integer> getAttackedCoordinatesList() {
        return attackedCoordinatesList;
    }

    public void setAttackedCoordinatesList(ArrayList<Integer> attackedCoordinatesList) {
        this.attackedCoordinatesList = attackedCoordinatesList;
    }

    public int getBoatsLeft() {
        return boatsLeft;
    }

    public void setBoatsLeft(int boatsLeft) {
        this.boatsLeft = boatsLeft;
    }
}
