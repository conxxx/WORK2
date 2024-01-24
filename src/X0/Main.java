//https://github.com/conxxx/WORK2.git
package X0;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select game type:");
        System.out.println("1 - Two autonomous players");
        System.out.println("2 - One autonomous player and one user-controlled player");

        int choice = scanner.nextInt();

        Game game;
        if (choice == 1) {
            game = new SelfGame();
        } else {
           game = new UserGame();
        }

        game.startGame();
    }
}
