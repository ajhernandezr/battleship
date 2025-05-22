import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(int gridSize) {
        this.player1 = new Player(gridSize);
        this.player2 = new Player(gridSize);
        this.currentPlayer = player1;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1, place your ships:");
        setupPlayerShips(player1, scanner);

        System.out.println("Player 2, place your ships:");
        setupPlayerShips(player2, scanner);

        while (true) {
            System.out.println("Current Player's Turn:");
            currentPlayer.displayGrid();

            System.out.print("Enter coordinates to shoot (x y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            Player opponent = (currentPlayer == player1) ? player2 : player1;
            boolean hit = opponent.takeShot(x, y);

            if (hit) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss!");
            }

            if (checkWinCondition(opponent)) {
                System.out.println("Game Over! Current Player Wins!");
                break;
            }

            switchTurn();
        }

        scanner.close();
    }

    private void setupPlayerShips(Player player, Scanner scanner) {
        System.out.println("Place your ships on the grid.");
        for (int i = 1; i <= 3; i++) { // Example: 3 ships of increasing size
            boolean placed = false;
            while (!placed) {
                System.out.printf("Enter starting coordinates (x y) and orientation (h/v) for ship of size %d: ", i);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                char orientation = scanner.next().charAt(0);
                boolean horizontal = (orientation == 'h');
                placed = player.placeShip(x, y, i, horizontal);
                if (!placed) {
                    System.out.println("Invalid placement. Try again.");
                }
            }
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean checkWinCondition(Player opponent) {
        return opponent.hasLost();
    }
}