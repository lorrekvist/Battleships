import java.util.ArrayList;

public class GameBoard implements IBoard {
    private ArrayList<Integer> boatPlacementsCoordsList = new ArrayList<Integer>(9);
    private ArrayList<Integer> bombedCoordinates = new ArrayList<Integer>(9);

    @Override
    public ArrayList<Integer> addBoatToBoard(int placementCoordinates) {
        if (boatPlacementsCoordsList.contains(placementCoordinates)){
            System.out.println("Coordinate already picked, please choose another.");
        } else {
            boatPlacementsCoordsList.add(placementCoordinates);
        }
        return boatPlacementsCoordsList;
    }

    @Override
    public boolean boardIsBombed(int bombedCoordinate) {
        bombedCoordinates.add(bombedCoordinate);
        if (boatPlacementsCoordsList.contains(bombedCoordinate)){
            System.out.println("Its a hit!");
            printBoardWhenBombed(bombedCoordinate, true);
            return true;
        } else {
            System.out.println("Its a miss!");
            printBoardWhenBombed(bombedCoordinate, false);
            return false;
        }
    }

    @Override
    public void printBoardWithBoats() {
        for(int i = 1; i < 10; i++) {
            if (boatPlacementsCoordsList.contains(i)) {
                System.out.print("B ");
            } else {
                System.out.print(i + " ");
            }
            if(i % 3 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public void printBoardWhenBombed(int bombedCoordinate, boolean hit) {
        for (int i = 1; i < 10; i++) {
            if (bombedCoordinates.contains(i)) {
                if (boatPlacementsCoordsList.contains(i)) {
                    System.out.print("X ");
                } else {
                    System.out.print("M ");
                }
            } else {
                System.out.print(i + " ");
            }
            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }

    public ArrayList<Integer> getBoatPlacementsCoordsList() {
        return boatPlacementsCoordsList;
    }

    public void setBoatPlacementsCoordsList(ArrayList<Integer> boatPlacementsCoordsList) {
        this.boatPlacementsCoordsList = boatPlacementsCoordsList;
    }

    public ArrayList<Integer> getBombedCoordinates() {
        return bombedCoordinates;
    }

    public void setBombedCoordinates(ArrayList<Integer> bombedCoordinates) {
        this.bombedCoordinates = bombedCoordinates;
    }
}
