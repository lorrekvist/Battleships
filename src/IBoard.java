import java.util.ArrayList;

public interface IBoard {
    ArrayList<Integer> boatPlacements(int placementCoord);
    boolean isHit(int hitCoord);
}
