import java.util.ArrayList;

public class Gameboard implements IBoard {
    ArrayList<Integer> placements = new ArrayList<Integer>(9);
    ArrayList<Integer> hits = new ArrayList<Integer>(9);
    int hitSquare;

    @Override
    public ArrayList<Integer> boatPlacements(int placementCoord) {
        if(placements.contains(placementCoord)){
            System.out.println("Youve already picked that coordinate. please choose another.");
        }else{
            placements.add(placementCoord);
        }
        return placements;
    }

    @Override
    public ArrayList<Integer> hitSquares(int hitCoord) {
        hits.add(hitCoord);
        return hits;
    }
    public boolean isHit(){
        return true;
    }
}
