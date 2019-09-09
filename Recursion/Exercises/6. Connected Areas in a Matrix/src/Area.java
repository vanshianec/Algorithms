public class Area {

    private int row;
    private int col;
    private int size;

    public Area(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d), size: %d", row, col, size);
    }
}
