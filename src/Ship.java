public class Ship {
    private final int size;
    private int hits;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
    }

    public void hit() {
        if (hits < size) {
            hits++;
        }
    }

    public boolean isSunk() {
        return hits == size;
    }

    public int getSize() {
        return size;
    }
}