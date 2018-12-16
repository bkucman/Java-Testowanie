package Projekt1;

import java.util.Scanner;

// Demo bez obługi wczytywania graczy chcę tylko sprawdzić samą rozgrywkę
public class Demo {
    public static void main( String[] args ) {
        int n = 0;
        Scanner inputSwitch = new Scanner(System.in);
        while (n != 5) {
            System.out.println("Choose:\n1-Play Game\n2-Print List of Players\n" +
                    "3-Sorted List Of Players\n"+
                    "5-Exit\n");
            n = inputSwitch.nextInt();
            switch (n) {
                case 1: {
                    System.out.println("Witaj w grze ");
                    Scanner input = new Scanner(System.in);

                    int player = 1;
                    int column;
                    String message;
                    int row,col;
                    Game game = null;
                    System.out.println("Get rows");
                    row = input.nextInt();
                    System.out.println("Get colums");
                    col = input.nextInt();

                    try {
                        game = new Game(row, col,true);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error "+e.getMessage());
                        break;
                    }
                    //Tu powinna być inicjacja graczy pętelka która pobiera nick i haslo aż będzie poprawne jak istniej
                    // lub tworzy nowego jak nie istnieje
                    while (game.win == 0) {
                        System.out.println(game.buildStringBoard() + "\n\nMove Player " + player + "\nPodaj kolumnę");

                        column = input.nextInt();

                        message = game.movePlayer(player, column);
                        System.out.println(message);
                        if (game.moveOK == false) {
                            System.out.println("Powtórz ruch");
                        } else {
                            if (player == 1) player = 2;
                            else player = 1;
                        }

                    }
                    System.out.println(game.buildStringBoard());
                    break;
                }
                case 2: {
                    Game game = new Game(6,7,true);
                    game.players = game.readListPlayers(null);

                    game.printListPlayers();
                    break;
                }
                case 3: {
                    Game game = new Game(6,7,true);
                    game.players = game.readListPlayers(null);
                    game.sortedList();
                    game.printListPlayers();
                    break;
                }
            }
        }
    }
}
