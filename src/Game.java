/*
Ett interface för Pre-game, alltså placering utav skeppen.
Ett interface for spelet, playerturn som hanterar bombningen av skepp.
Abstrakta klassen hanterar båten
 */


import javax.management.monitor.GaugeMonitor;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    GameHelper gameHelper = new GameHelper();

    public void start() {
        boolean quit = false;
        int menuItem = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to battleships!");

        do {

            System.out.print("Choose 1 for PvP 2 for PvE");
            try{
                menuItem = scan.nextInt();
            }
            catch(Exception InputMismatchException){
                System.out.println("Please choose 1 or 2");
                if (menuItem == 0) {
                    start();
                }
            }



            switch (menuItem) {

                case 1:
                    System.out.println("You've chosen PvP");

                    initiateGame(true);
                    break;

                case 2:

                    initiateGame(false);
                    System.out.println("You've chosen item #2");
                    break;

                case 3:

                    quit = true;

                    break;

                default:
                    menuItem = 0;
                    System.out.println("Invalid choice.");

            }

        } while (!quit);
    }
    public void initiateGame(boolean pvpGame){
        Player playerOne = new Player();
        Gameboard playerOneBoard = new Gameboard();
        do {
            playerOneBoard.boatPlacements(gameHelper.getCoordinates());
            System.out.println(playerOneBoard.placements);
        }while(playerOneBoard.placements.size()<=3);
        if(pvpGame){
            initiatePvPGame(playerOne, playerOneBoard);
        }
        else{
            initiatePvEGame(playerOne, playerOneBoard);
        }

    }

    public void initiatePvPGame(Player playerOne, Gameboard playerOneBoard){
        Player playerTwo = new Player();
        Gameboard playerTwoBoard = new Gameboard();
        do {
            playerTwoBoard.boatPlacements(gameHelper.getCoordinates());
            System.out.println(playerTwoBoard.placements);
        }while(playerTwoBoard.placements.size()<=3);
        System.out.println("Game is on");
        startPvPWar(playerOne, playerTwo, playerOneBoard, playerTwoBoard);
    }

    public void initiatePvEGame(Player playerOne, Gameboard playerOneBoard){
        Player bot = new Player();
        Gameboard botBoard = new Gameboard();
        Random random = new Random();
        for(int i = 0; i <= 3 ; i++){
            int randomInt = random.nextInt(9)+1;
            if(botBoard.placements.contains(randomInt)){
                i--;
            }else {
                botBoard.placements.add(randomInt);
            }
    }
        System.out.println(botBoard.placements);
        //startPvEWar(playerOne, bot, playerOneBoard, botBoard);

    }

    public void startPvPWar(Player playerOne, Player playerTwo, Gameboard playerOneBoard, Gameboard playerTwoBoard){
        int round = 0;
        do{
            if(round % 2 == 0) {
                if (playerOne.attackCoordList.size() < 0) {
                    System.out.println("Your shots: " + playerOne.attackCoordList);
                }
                int shotSquare = gameHelper.PlayerShoot(playerOne.attackCoordList);
                if(playerTwoBoard.hitSquares(shotSquare)) {
                    playerOne.playerLives--;
                }
                playerOne.attackCoordList.add(shotSquare);
                round++;
                System.out.println(playerOne.inPlay());
                System.out.println(playerTwo.inPlay());
            } else {
                if (playerTwo.attackCoordList.size() < 0) {
                    System.out.println("Your shots: " + playerTwo.attackCoordList);
                }
                int shotSquare = gameHelper.PlayerShoot(playerTwo.attackCoordList);
                if(playerOneBoard.hitSquares(shotSquare)) {
                    playerTwo.playerLives--;
                }
                round++;
            }
        }while(playerOne.inPlay() && playerTwo.inPlay());
    }
}

