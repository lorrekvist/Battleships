import java.util.ArrayList;

public class Gameboard implements IBoard {
    ArrayList<Integer> boatPlacementsCoordsList = new ArrayList<Integer>(9);
    ArrayList<Integer> hits = new ArrayList<Integer>(9);

    @Override
    public ArrayList<Integer> boatPlacements(int placementCoord) {
        if (boatPlacementsCoordsList.contains(placementCoord)){
            System.out.println("Coordinate already picked, please choose another.");
        } else {
            boatPlacementsCoordsList.add(placementCoord);
        }
        return boatPlacementsCoordsList;
    }

    @Override
    public boolean isHit(int hitCoord) {
        hits.add(hitCoord);
        if (boatPlacementsCoordsList.contains(hitCoord)){
            System.out.println("Its a hit!");
            return true;
        } else {
            System.out.println("Its a miss!");
            return false;
        }
    }
}
