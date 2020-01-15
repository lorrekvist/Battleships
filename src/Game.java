/*
Ett interface för Pre-game, alltså placering utav skeppen.
Ett interface for spelet, playerturn som hanterar bombningen av skepp.
Abstrakta klassen hanterar båten
 */


import java.util.Random;
import java.util.Scanner;

public class Game {
    GameHelper gameHelper = new GameHelper();

    protected void start() {
        boolean quit = false;
        int menuItem = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to battleships!");

        do {
            System.out.print("Choose 1 for PvP or 2 for PvE (3 to quit game): ");
            try{
                menuItem = scan.nextInt();
            }
            catch(Exception InputMismatchException){
                System.out.println("Invalid choice. You can only chose either 1 for PvP or 2 for PvE: ");
                if (menuItem == 0) {
                    start();
                }
            }

            switch (menuItem) {
                case 1:
                    System.out.println("PvP-mode chosen.");
                    initiatePlayerOne(true);
                    break;
                case 2:
                    System.out.println("PvE-mode chosen.");
                    initiatePlayerOne(false);
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    menuItem = 0;
                    System.out.println("Invalid choice. You can only chose either 1 for PvP or 2 for PvE: ");
            }
        } while (!quit);
    }

    private void initiatePlayerOne(boolean pvpGame){
        Player playerOne = new Player();
        Gameboard playerOneBoard = new Gameboard();
        do {
            System.out.println("Player one choosing coordinate to place boat on.");
            playerOneBoard.boatPlacements(gameHelper.getCoordinates());
            System.out.println("Player one placements: " + playerOneBoard.boatPlacementsCoordsList);
        } while(playerOneBoard.boatPlacementsCoordsList.size() <= 3);
        if (pvpGame){
            startPvPGame(playerOne, playerOneBoard);
        } else {
            startPvEGame(playerOne, playerOneBoard);
        }
    }

    private void startPvPGame(Player playerOne, Gameboard playerOneBoard){
        Player playerTwo = new Player();
        Gameboard playerTwoBoard = new Gameboard();
        do {
            System.out.println("Player two choosing coordinate to place boat on.");
            playerTwoBoard.boatPlacements(gameHelper.getCoordinates());
            System.out.println("Player two placements: " + playerTwoBoard.boatPlacementsCoordsList);
        }while(playerTwoBoard.boatPlacementsCoordsList.size()<=3);
        System.out.println("Game is live!");
        startPvPWar(playerOne, playerTwo, playerOneBoard, playerTwoBoard);
    }

    private void startPvEGame(Player playerOne, Gameboard playerOneBoard){
        Player bot = new Player();
        Gameboard botBoard = new Gameboard();
        Random random = new Random();
        for(int i = 0; i <= 3 ; i++){
            int randomInt = random.nextInt(9)+1;
            if(botBoard.boatPlacementsCoordsList.contains(randomInt)){
                i--;
            }else {
                botBoard.boatPlacementsCoordsList.add(randomInt);
            }
    }
        System.out.println(botBoard.boatPlacementsCoordsList);
        //startPvEWar(playerOne, bot, playerOneBoard, botBoard);

    }

    private void startPvPWar(Player playerOne, Player playerTwo, Gameboard playerOneBoard, Gameboard playerTwoBoard){
        int round = 0;
        do{
            if(round % 2 == 0) {
                if (playerOne.attackCoordList.size() < 0) {
                    System.out.println("Your shots: " + playerOne.attackCoordList);
                }
                int shotSquare = gameHelper.PlayerShoot(playerOne.attackCoordList, "one");
                playerOne.attackCoordList.add(shotSquare);
                if(playerTwoBoard.isHit(shotSquare)) {
                    playerTwo.playerLives--;
                }
                playerOne.attackCoordList.add(shotSquare);
            } else {
                if (playerTwo.attackCoordList.size() < 0) {
                    System.out.println("Your shots: " + playerTwo.attackCoordList);
                }
                int shotSquare = gameHelper.PlayerShoot(playerTwo.attackCoordList, "two");
                playerTwo.attackCoordList.add(shotSquare);
                if(playerOneBoard.isHit(shotSquare)) {
                    playerOne.playerLives--;
                }
            }
            round++;
        } while(playerOne.inPlay() && playerTwo.inPlay());

        if (playerOne.inPlay()) {
            System.out.println("Player one is the winner!");
        } else {
            System.out.println("Player two is the winner!");
        }
        start();
    }
}

