import java.util.InputMismatchException;
import java.util.Scanner;

import player.Player;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Player p = new Player("DEFAULT");
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("Hello " + p.getPlayerName() + ".");
            System.out.print("Enter a new name: ");

            try {
                String name = scan.nextLine();
                System.out.println();
                p.setPlayerName(name);
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid name");
            } finally {
                System.out.println("Your new name is: " + p.getPlayerName() + ".");
                System.out.println();
                if (p.getPlayerName().equals("gameover")) {
                    gameOver = true;
                }
            }
        }
        System.out.println("GAME OVER!");
        scan.next();
        scan.close();
    }
}
