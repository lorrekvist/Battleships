import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IBoard {
    ArrayList<Integer> boatPlacements(int placementCoord);
    boolean hitSquares(int hitCoord);
    boolean isHit();
}
