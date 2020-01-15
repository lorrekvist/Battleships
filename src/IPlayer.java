import java.util.ArrayList;

public interface IPlayer {
    int lifeCounter = 4;
    boolean inPlay();
    ArrayList<Integer> attackCoords();
}
