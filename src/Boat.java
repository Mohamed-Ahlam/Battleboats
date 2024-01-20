//Ahlam Mohamed Moha0769

public class Boat {
    private int row;
    private int col;
    private int boatLength;
    private boolean orientation, check, randomBoolean;
    private Cell[] boatCellArray;       // cell arrays that tell u where the boats are in the board
                            // ex [{0,1}{0,2},{0.3}{9,1}{9,2}{9,3}]

    public Boat(int row, int col, int size, boolean orientation){
        this.col = col;
        this.row = row;
        this.boatLength = size;
        this.orientation = orientation;
    }

    public void setBoatLength(int boatLength) {
        this.boatLength = boatLength;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }


    public void setBoatCellArray(Cell[] boatCellArray) {
        this.boatCellArray = boatCellArray;
    }

    public void getBoatCellArray(Boat boat, int x, int y) {}


    public int getBoatLength() {
        return boatLength;
    }

    public boolean getOrientation() {
        return orientation;
    }
}
