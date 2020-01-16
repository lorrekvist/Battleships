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
        GameBoard playerOneBoard = new GameBoard();
        do {
            System.out.println("Player one choosing coordinate to place boat on.");
            playerOneBoard.addBoatToBoard(gameHelper.getCoordinates());
            System.out.println("Player one placements: " + playerOneBoard.getBoatPlacementsCoordsList());
        } while(playerOneBoard.getBoatPlacementsCoordsList().size() <= 3);
        playerOneBoard.printBoardWithBoats();
        if (pvpGame){
            startPvPGame(playerOne, playerOneBoard);
        } else {
            startPvEGame(playerOne, playerOneBoard);
        }
    }

    private void startPvPGame(Player playerOne, GameBoard playerOneBoard){
        Player playerTwo = new Player();
        GameBoard playerTwoBoard = new GameBoard();
        do {
            System.out.println("Player two choosing coordinate to place boat on.");
            playerTwoBoard.addBoatToBoard(gameHelper.getCoordinates());
            System.out.println("Player two placements: " + playerTwoBoard.getBoatPlacementsCoordsList());
        } while(playerTwoBoard.getBoatPlacementsCoordsList().size() <= 3);
        playerTwoBoard.printBoardWithBoats();
        System.out.println("Game is live!");
        startPvPWar(playerOne, playerTwo, playerOneBoard, playerTwoBoard);
    }

    private void startPvEGame(Player playerOne, GameBoard playerOneBoard){
        Player bot = new Player();
        GameBoard botBoard = new GameBoard();
        gameHelper.addBoatsToBotBoatPlacementsList(botBoard.getBoatPlacementsCoordsList());
        System.out.println("Bot placements: " + botBoard.getBoatPlacementsCoordsList());
        botBoard.printBoardWithBoats();
        System.out.println("Game is live!");
        startPvEWar(playerOne, bot, playerOneBoard, botBoard);
    }

    private void startPvPWar(Player playerOne, Player playerTwo, GameBoard playerOneBoard, GameBoard playerTwoBoard){
        int round = 0;
        do{
            if(round % 2 == 0) {
                gameHelper.playerAttackMove(playerOne, playerTwo, playerTwoBoard, "one");
            } else {
                gameHelper.playerAttackMove(playerTwo, playerOne, playerOneBoard, "two");
            }
            round++;
        } while(playerOne.hasBoatsLeft() && playerTwo.hasBoatsLeft());

        if (playerOne.hasBoatsLeft()) {
            System.out.println("Player one is the winner!");
        } else {
            System.out.println("Player two is the winner!");
        }
        start();
    }

    private void startPvEWar(Player playerOne, Player bot, GameBoard playerOneBoard, GameBoard botBoard) {
        int round = 0;
        do{
            if(round % 2 == 0) {
                gameHelper.playerAttackMove(playerOne, bot, botBoard, "one");
            } else {
                gameHelper.botAttackMove(bot, playerOne, playerOneBoard);
            }
            round++;
        } while(playerOne.hasBoatsLeft() && bot.hasBoatsLeft());

        if (playerOne.hasBoatsLeft()) {
            System.out.println("Player one is the winner!");
        } else {
            System.out.println("Bot is the winner!");
        }
        start();
    }
}

