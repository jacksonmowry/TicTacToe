import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
class ttt {
    public static void main(String[] args) {
        // Setting up the input and game board
        Scanner s = new Scanner(System.in);
        String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
        String[] boardNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        GameMoves.printBoard(boardNum);

        // Main game loop
        while (true) {

            // Checking for a full board
            ArrayList boardFull = new ArrayList();
            for (int i = 0; i < board.length; i++) {
                String cha = board[i];
                boardFull.add(cha);
            }

            // If game has been won/board = full then game over
            if (GameMoves.winCondition(board, "x") == true) {
                System.out.println("Game Over");
                System.out.println("You Win!");
                System.out.print("Press Enter to Exit!");
                s.nextLine(); s.nextLine();
                break;
            }
            else if (GameMoves.winCondition(board, "o") == true) {
                System.out.println("Game Over");
                System.out.println("You Lose!");
                System.out.print("Press Enter to Exit!");
                s.nextLine(); s.nextLine();
                break;
            }
            else if (boardFull.contains(" ") == false) {
                System.out.println("Cat's Game");
                System.out.print("Press Enter to Exit!");
                s.nextLine(); s.nextLine();
                break;
            }

            // Otherwise the player will pick a square followed by the CPU choice
            else {
                System.out.print("Please pick a square!: ");
                int choice = s.nextInt() - 1;
                GameMoves.setLetter(board, choice, "x");
                int cpuPos = GameMoves.cpuMove(board);
                GameMoves.setLetter(board, cpuPos, "o");
            }

            // The board is printed after each turn
            GameMoves.printBoard(board);
        }
    }
}

class GameMoves {

    public static int getRandom(ArrayList lst) {
        System.out.println(lst.size());
        int random = new Random().nextInt(lst.size());
        System.out.println(random);
        return (int)lst.get(random);
    }

    public static String getLetter(String[] currentBoard, int pos) {
        // code
        return (currentBoard[pos]);
    }

    public static void setLetter(String[] currentBoard, int pos, String letter) {
        String position = currentBoard[pos];
        if (position.equals(" ")) {
            currentBoard[pos] = letter;
        }
    }

    public static void printBoard(String[] currentBoard) {
        System.out.printf("%1s | %1s | %1s%n%1s | %1s | %1s%n%1s | %1s | %1s%n", currentBoard[0], currentBoard[1], currentBoard[2], currentBoard[3], currentBoard[4], currentBoard[5], currentBoard[6], currentBoard[7], currentBoard[8]);
    }

    public static boolean winCondition(String[] c, String l) {
        String cha = (l + l + l);

        String rowOne = (c[0] + c[1] + c[2]);
        String rowTwo = (c[3] + c[4] + c[5]);
        String rowThr = (c[6] + c[7] + c[8]);

        String colOne = (c[0] + c[3] + c[6]);
        String colTwo = (c[1] + c[4] + c[7]);
        String colThr = (c[2] + c[5] + c[8]);

        String rightLeft = (c[0] + c[4] + c[8]);
        String leftRight = (c[2] + c[4] + c[6]);

        if (rowOne.equals(cha) || rowTwo.equals(cha) || rowThr.equals(cha)|| colOne.equals(cha) ||
            colTwo.equals(cha) || colThr.equals(cha) || rightLeft.equals(cha) || leftRight.equals(cha)) {
            return true;
        }
        else {
            return false;
        }
    }


    public static int cpuMove(String[] currentBoard) {
        int move = 0;

        ArrayList cpu = new ArrayList();
        for (int i = 0; i < currentBoard.length; i++) {
            if (currentBoard[i].equals(" ")) {
                cpu.add(i);
            }
        }
        // CPU checking for self winCondition
        for (int i = 0; i < cpu.size(); i++) {
            int choice = (int)cpu.get(i);
            String[] copy = currentBoard.clone();
            copy[choice] = "o";
            if (winCondition (copy, "o") == true) {
                move = (int)cpu.get(i);
                return move;
            }

        }

        // CPU checking for player winCondition
        for (int i = 0; i < cpu.size(); i++) {
            int choice = (int)cpu.get(i);
            String[] copy = currentBoard.clone();
            copy[choice] = "x";
            if (winCondition (copy, "x") == true) {
                move = (int)cpu.get(i);
                return move;
            }
        }

        // CPU checks corner
        ArrayList cornersOpen = new ArrayList();
        ArrayList corners = new ArrayList();
        corners.add(0);
        corners.add(2);
        corners.add(6);
        corners.add(8);
        for (int i = 0; i < cpu.size(); i++) {       //TODO make this actually random
            int choice = (int)cpu.get(i);
            if (corners.contains(choice) == true) {
                cornersOpen.add((int)cpu.get(i));
            }
        }
        if (cornersOpen.size() > 0) {
            move = (int)getRandom(cornersOpen);
            return move;
        }

        // CPU Center
        if (cpu.contains(4) == true) {
            move = 4;
            return move;
        }

        // CPU Edge
        ArrayList edgeOpen = new ArrayList();
        ArrayList edge = new ArrayList();
        edge.add(1);
        edge.add(3);
        edge.add(5);
        edge.add(7);
        for (int i = 0; i < cpu.size(); i++) {
            int choice = (int)cpu.get(i);
            if (edge.contains(choice) == true) {
                edgeOpen.add((int)cpu.get(i));
            }
        }
        if (edgeOpen.size() > 0) {
            move = (int)getRandom(edgeOpen);
            return move;
        }


        return move;
    }
}
