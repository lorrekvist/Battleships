import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
    // PROBLEM MAN KAN INTE VÄLJA ALLA RUTOR
    public int getCoordinates(){
        int coordinate = 0;

        do{
            try{
                System.out.print("Input square number, 1-9: ");
                coordinate = new Scanner(System.in).nextInt();
            }catch(Exception InputMismatchException){
                System.out.println("Please input an integer 1-9, nothing else.");
            }
        }while(coordinate > 9 || coordinate < 1);

        return coordinate;
    }

    public int sendBomb(ArrayList<Integer> attackCoordinationList, String player) {
        System.out.println("Player " + player + " choosing coordinates to bomb.");
        int currentShot = getCoordinates();
        if(attackCoordinationList.contains(currentShot)){
            do {
                System.out.println("You've already fired on this position, pick another.");
                currentShot = getCoordinates();
            } while(attackCoordinationList.contains(currentShot));
        }
        return currentShot;
    }

    public int sendRandomBomb(ArrayList<Integer> attackCoordinationList) {
        int randomInt = 0;
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            randomInt = random.nextInt(9) + 1;
            if (attackCoordinationList.contains(randomInt)){
                i--;
            } else {
                break;
            }
        }
        return(randomInt);
    }

    public void addBoatsToBotBoatPlacementsList(ArrayList<Integer> botBoatPlacementsCoordsList) {
        Random random = new Random();
        for(int i = 0; i <= 3 ; i++){
            int randomInt = random.nextInt(9) + 1;
            if(botBoatPlacementsCoordsList.contains(randomInt)){
                i--;
            } else {
                botBoatPlacementsCoordsList.add(randomInt);
            }
        }
    }

    public void playerAttackMove(Player attackingPlayer, Player attackedPlayer, GameBoard attackedBoard) {
        int shotSquare = sendBomb(attackingPlayer.attackedCoordinatesList, "one");
        attackingPlayer.attackedCoordinatesList.add(shotSquare);
        if(attackedBoard.boardIsBombed(shotSquare)) {
            attackedPlayer.boatsLeft--;
        }
        System.out.println("Your shots: " + attackingPlayer.attackedCoordinatesList);
        // PRINTA MOTSTÅNDARENS SPELPLAN MED X FÖR TRÄFF M FÖR MISS 0 FÖR ICKE BOMBADE SQUARES
    }

    public void botAttackMove(Player bot, Player attackedPlayer, GameBoard attackedBoard) {
        int shotSquare = sendRandomBomb(bot.attackedCoordinatesList);
        bot.attackedCoordinatesList.add(shotSquare);
        if(attackedBoard.boardIsBombed(shotSquare)) {
            attackedPlayer.boatsLeft--;
        }
        System.out.println("Bots shots: " + bot.attackedCoordinatesList);
        // PRINTA SPELARENS SPELPLAN MED X FÖR TRÄFF M FÖR MISS 0 FÖR ICKE BOMBADE SQUARES
    }
}
