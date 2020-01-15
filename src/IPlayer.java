import java.util.ArrayList;

public interface IPlayer {
    int boats = 4;
    boolean hasBoatsLeft();
    ArrayList<Integer> coordinatesAttacked();
}
