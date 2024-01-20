//Ahlam Mohamed Moha0769

public class Cell {
    private int row;
    private int col;
    private char status;

    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getStatus() {
        return status;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
