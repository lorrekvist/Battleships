import java.util.Scanner;

public class GameHelper {
    public int getCoordinates(){
        int xCoord = 0;
        int yCoord = 0;

        do{
            try{
                System.out.println("Input X coordinate, 1-3: ");
                xCoord = new Scanner(System.in).nextInt();
            }catch(Exception InputMismatchException){
                System.out.println("Please input an integer 1-3, nothing else.");
                getCoordinates();
            }

        }while(xCoord > 3 || xCoord < 1);
        do{
            try{
                System.out.println("Input Y coordinate, 1-3: ");
                yCoord = new Scanner(System.in).nextInt();
            }catch(Exception InputMismatchException){
                System.out.println("Please input an integer 1-3, nothing else.");
                getCoordinates();
            }
        }while(yCoord > 3 || yCoord < 1);

        return xCoord * yCoord;
    }

    public int PlayerOneShoot() {
        System.out.println("Player one choose coordinate to bomb: ");
        return getCoordinates();
    }

    public int PlayerTwoShoot() {
        System.out.println("Player two choose coordinate to bomb: ");
        return getCoordinates();
    }
}
