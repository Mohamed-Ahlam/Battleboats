import java.util.Random;
import java.util.Scanner;
// Ahlam Mohamed Moha0769

public class Board {
    private Cell[][] board;
    private Boat[] boat;
    private int row, col, randomOrientation, randomCellRow, randomCellCol, turns, numBoats;
    private int count = 0;


    public Board(int row, int col) {
        this.row = row;
        this.col = col;

        board = new Cell[row][col];
        for (int i = 0; i < row; i++) {        //  make a board of empty cells based on the type of board there is. makes each cell have a '-' status
            for (int j = 0; j < col; j++) {
                board[i][j] = new Cell(i, j, '-');
            }
        }
        placeBoats();       // calls method to help set up the board
    }

    public void placeBoats() { // randomly places boats in cell[][] array

        int[] boatSizeArray;  // this array is used to hold the sizes each boat will have depending on the board
        int num;
        boolean check;

        num = Math.min(row, col);    // choose the smallest between the row or collum and use that to see how many boats and how long each one will be

        if (num == 3) {       // choose the boat size array based on num
            boatSizeArray = new int[]{2};
            boat = new Boat[2];
        } else if (3 < num && num <= 4) {
            boatSizeArray = new int[]{2, 3};
            boat = new Boat[5];
        } else if (4 < num && num <= 6) {
            boatSizeArray = new int[]{2, 3, 3};
            boat = new Boat[8];
        } else if (6 < num && num <= 8) {
            boatSizeArray = new int[]{2, 3, 3, 4};
            boat = new Boat[12];
        } else {
            boatSizeArray = new int[]{2, 3, 3, 4, 5};
            boat = new Boat[17];
        }

        for (int i = 0; i < boatSizeArray.length; i++) {           // loops until all boats are in board by going through boatSizeArray
            check = false;
            Random random = new Random();
            //
            while (check == false) {     // u will exit this loop ONLY when u finish making 1 full boat

                randomOrientation = random.nextInt(2);      // this will decide if the boat will be verticle = 1 or horizontal = 0
                if (randomOrientation == 0) { // go horizontal

                    randomCellRow = random.nextInt(board.length - boatSizeArray[i]);
                    randomCellCol = random.nextInt(col);

                    for (int j = 0; j < boatSizeArray[i]; j++) {        // check if the coordinates work and if u can continue building horizontally
                        if (randomCellRow + j < row && randomCellRow + j >= 0 && board[randomCellRow + j][randomCellCol].getStatus() != 'B') {
                            check = true;
                        } else {
                            check = false;
                            break;
                        }
                    }
                }
                if (randomOrientation == 1) {    // goes verticle

                    randomCellRow = random.nextInt(row);  // check if it works
                    randomCellCol = random.nextInt(board[0].length - boatSizeArray[i]);

                    for (int j = 0; j < boatSizeArray[i]; j++) {        // check if the coordinates work and if u can continue building vertically
                        if (randomCellCol + j < col && randomCellCol + j >= 0 && board[randomCellRow][randomCellCol + j].getStatus() != 'B') {
                            check = true;
                        } else {
                            check = false;
                            break;
                        }
                    }
                }
            }

            if (randomOrientation == 0) {   // if it can go horizontal then it will place boats by setting the status to b
                for (int j = 0; j < boatSizeArray[i]; j++) {
                    board[randomCellRow + j][randomCellCol].setStatus('B');
                    boat[count] = new Boat(randomCellRow + j, randomCellCol, boatSizeArray[i], false);
                    count++;
                }
            }
            if (randomOrientation == 1) {  // if it can go verticle then it will place boats by setting the status to b
                for (int j = 0; j < boatSizeArray[i]; j++) {
                    board[randomCellRow][randomCellCol + j].setStatus('B');
                    boat[count] = new Boat(randomCellRow, randomCellCol+j, boatSizeArray[i], true);
                    count++;
                }
            }
        }
    }

    public void fire(int x, int y) {        // checks the status of the coordinates put in then decides what to do

        if(x>row || x<0 || y>col || y<0){       // penalty if input is out of bounds
            System.out.println("Input out of bounds. You will lose a turn");
            turns++;
        }
        else if (board[x][y].getStatus() == '-') {
            board[x][y].setStatus('M');

            System.out.println("Miss");
        }
        else if (board[x][y].getStatus() == 'B') {
            board[x][y].setStatus('H');
            System.out.println("Boat hit");
        }
        else if (board[x][y].getStatus() == 'H') {      // penalty if input is already hit
            System.out.println("Boat has been hit already. You will lose a turn");
            turns++;
        }
        else if (board[x][y].getStatus() == 'M') {          // penalty if input is already guessed
            System.out.println("Coordinates have been guessed already. You will lose a turn");
            turns++;
        }
    }

    public void missile(int x, int y) {          // method will take input and set it and everything around it going 3x3 to a hit status
        if (x < row && x >= 0 && y < col && y >= 0) {
            board[x][y].setStatus('H');
        }
        if (x + 1 < row && x + 1 >= 0 && y < col && y >= 0) {
            board[x + 1][y].setStatus('H');
        }
        if (x - 1 < row && x - 1 >= 0 && y < col && y >= 0) {
            board[x - 1][y].setStatus('H');
        }
        if (x < row && x >= 0 && y - 1 < col && y - 1 >= 0) {
            board[x][y - 1].setStatus('H');
        }
        if (x < row && x >= 0 && y + 1 < col && y + 1 >= 0) {
            board[x][y + 1].setStatus('H');
        }
        if (x - 1 < row && x - 1 >= 0 && y - 1 < col && y - 1 >= 0) {
            board[x - 1][y - 1].setStatus('H');
        }
        if (x + 1 < row && x + 1 >= 0 && y + 1 < col && y + 1 >= 0) {
            board[x + 1][y + 1].setStatus('H');

        }
        if (x + 1 < row && x + 1 >= 0 && y - 1 < col && y - 1 >= 0) {
            board[x + 1][y - 1].setStatus('H');
        }

        if (x - 1 < row && x - 1 >= 0 && y + 1 < col && y + 1 >= 0) {
            board[x - 1][y + 1].setStatus('H');
        }
        System.out.println("Missile has been launched");
    }

    public void print() {                  // method prints out everything to the user including boats // will be used for the debug mode
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(board[i][j].getStatus());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void display() {                      // prints board hits misses and empty epaces but not where the boats are
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getStatus() == 'B') {
                    System.out.print("-");
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].getStatus());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void drone(String choice) {           // takes in r: row or c: col then it
        boolean check = true;
        int count = 0;
        Scanner scanner = new Scanner(System.in);

        if (choice.equals("r")) {
            System.out.println("What row would you like to scan?");     // asks the user which one it should scan
            while (check == true) {
                int scan = scanner.nextInt();
                if (scan > row || scan < 0) {
                    System.out.println("Invalid input. Please type in a number within the boundaries");     // loop until the user returns the correct input
                } else {
                    for (int i = 0; i < row; i++) {
                        if (board[scan][i].getStatus() == 'B') {
                            count++;
                        }
                    }
                    check = false;
                }
            }

        } else {
            System.out.println("What col would you like to scan?");
            while (check == true) {
                int scan = scanner.nextInt();
                if (scan > col || scan < 0) {
                    System.out.println("Invalid input. Please type in a number within the boundaries");
                } else {
                    for (int i = 0; i < col; i++) {
                        if (board[i][scan].getStatus() == 'B') {
                            count++;
                        }
                    }
                    check = false;
                }
            }
        }

        System.out.println("Drone has scanned " + count + " targets in the specified area");        // returns the amount of boats found where it scanned
    }

    public void scanner() {                 // prints orientaion, status, size of the coordinates inputed by the user
        boolean orientation = false;
        char status = 0;
        int size = 0;
        boolean foundBoat = false;

        System.out.println("What coordinate would you like to scan");
        Scanner scanner = new Scanner(System.in);
        int scanRow = scanner.nextInt();
        int scanCol = scanner.nextInt();


        if (scanRow > row || scanRow < 0 || scanCol > col || scanCol < 0) {             // if the input is out of bounds user will lose a turn
            System.out.println("Input out of bounds. You will loose a turn");
                turns++;
        } else {
            for (int i = 0; i < boat.length; i++) {                     // loops boat array to see if the user input is a boat
                if (boat[i].getRow() == scanRow && boat[i].getCol() == scanCol) {     // for example boat[0] = boat12 use the .getRow and getCol to access its x and y coordinates
                    foundBoat = true;
                    status = board[scanRow][scanCol].getStatus();       //if the boat is found its orientaion, status, size is saved and printed out below
                    orientation = boat[i].getOrientation();
                    size = boat[i].getBoatLength();
                }
            }
        }
        if(foundBoat == true) {         // if the boat is found in boat array then print out its attributes
            if (orientation == true)
                System.out.println("The boat status is " + status + " The boat is horizontally placed and its size is " + size);
            if (orientation == false)
                System.out.println("The boat status is " + status + " The boat is horizontally placed and its size is " + size);
        }
        else
            System.out.println("The was no boat found in these coordinates");
    }

    public boolean sunk(){      // method used to keep track of all boats and game ends when all boats are hit
        for(int i = 0; i<boat.length;i++){
            int x = boat[i].getRow();
            int y = boat[i].getCol();
            if(board[x][y].getStatus() == 'B'){
                return false;
            }
        }
        return true;
    }
    public void turns(){        // keeps track of turns and is called everytime a user puts in input
        System.out.println();
        System.out.println("Turn " + turns);
        turns++;
    }
    public int getTurn(){           // used at the end of the game to see how many turns it took to finish the game
        return turns;
    }
    public int getNumBoats(){           // used to tell the user how many boats are on the board at the beggining of the game
        for(int i = 0; i< boat.length; i++){
             numBoats++;
        }
        return numBoats;
    }
}
