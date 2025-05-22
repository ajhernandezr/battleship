import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Grid grid;
    private final List<Ship> ships;

    public Player(int gridSize) {
        this.grid = new Grid(gridSize);
        this.ships = new ArrayList<>();
    }

    public boolean placeShip(int x, int y, int length, boolean horizontal) {
        if (grid.placeShip(x, y, length, horizontal)) {
            ships.add(new Ship(length));
            return true;
        }
        return false;
    }

    public boolean takeShot(int x, int y) {
        if (grid.takeShot(x, y)) {
            for (Ship ship : ships) {
                if (!ship.isSunk()) {
                    ship.hit();
                    break;
                }
            }
            return true; // Hit
        }
        return false; // Miss
    }

    public boolean hasLost() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public void displayGrid() {
        grid.displayBoard();
    }
}