import java.util.Scanner;
//Ahlam Mohamed Moha0769
public class Game {

    public static void main(String[] args) {
        int row = 0;
        int col =0;
        int mode = 0;
        String userInput = null;
        boolean check = false;
        boolean modeCheck = false;
        boolean coordinatesCheck = false;
        boolean gotCorrectInput = false;
        Board board = null;

        System.out.println("To play the game type in f to hit boats " + "\n" + "To use the powers type d to drone, m to missile, s to scan");
        System.out.println();
        System.out.println("First choose game mode: debug mode: 1 and normal mode: 2");
        Scanner scanner = new Scanner(System.in);

        while(modeCheck == false) {             // loops until a valid mode is choosen, normal = 2 and debug = 1 and this is used to print out the method display or print
            mode = scanner.nextInt();
            if(mode == 1 || mode == 2)
                modeCheck = true;
            else
                System.out.println("Wrong mode inputed");
        }

        System.out.println("Please create the board dimensions");

        while (coordinatesCheck == false) {        // loops until a valid row and col is used to make the boat
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row < 11 && row > 2 && col < 11 && col > 2) {
                board = new Board(row, col);
                coordinatesCheck = true;
            } else
                System.out.println("Invalid input. Please try again");
        }
        System.out.println("Board created. There are " + board.getNumBoats()+ " boats on the board. Remember to type in f,m,d or s to play");
        System.out.println();

        while (check == false) {            // loops until all boats have been hit
            System.out.println();
            gotCorrectInput = false;

            if(mode == 1){
                board.print();
            }
            if(mode == 2){
                board.display();
            }
            board.turns();          // prints out the turn each time user plays
            
            while(gotCorrectInput == false) {           // loops until the user inputs the valid input to play the game
                userInput = scanner.next();
                if(userInput.equals("f") || userInput.equals("d")|| userInput.equals("m")|| userInput.equals("s"))
                    gotCorrectInput = true;
                else
                    System.out.println("Invalid input please try again");
            }
            if(userInput.equals("f")) {
                System.out.println("Type in two coordinates");
                int fireOnRow = scanner.nextInt();
                int fireOnCol = scanner.nextInt();

              board.fire(fireOnRow, fireOnCol);
            }

            else if(userInput.equals("d")){
                boolean droneCheck = false;
                String choice = "";

                System.out.println("Please type in r for row or c for col");
                while(droneCheck == false) {
                    choice = scanner.next();
                    if(choice.equals("r")|| choice.equals("c"))
                        droneCheck = true;
                    else
                        System.out.println("Invalid input please try again");
                }
                    board.drone(choice);
            }

            else if(userInput.equals("m")) {
                boolean isValid = false;
                System.out.println("Where would you like to launch your missle?");
                while(isValid == false) {                                               // loops until a valid input is made
                    int missleOnRow = scanner.nextInt();
                    int missleOnCol = scanner.nextInt();
                    if(missleOnRow < row && missleOnRow >= 0 && missleOnCol >= 0 && missleOnCol<col) {
                        board.missile(missleOnRow, missleOnCol);
                        isValid = true;
                    }
                    else
                        System.out.println("Invalid input please retry again");
            }

            }

            else if(userInput.equals("s")){
                board.scanner();
            }

            check = board.sunk();
        }

            System.out.println("Game ended. Thanks for playing!");
            System.out.println();
            System.out.println("You took "+ board.getTurn() +" turns to finish the game");
    }
}